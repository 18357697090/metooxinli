package com.metoo.order.nr.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.REPage;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.order.NrBackpackApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserApi;
import com.metoo.order.nr.dao.entity.NrBackpack;
import com.metoo.order.nr.dao.entity.NrGoods;
import com.metoo.order.nr.service.NrBackpackService;
import com.metoo.order.nr.service.NrGoodsService;
import com.metoo.pojo.nr.vo.NrGoodsVo;
import com.metoo.pojo.old.vo.BackpackDTO;
import com.metoo.pojo.order.model.NrBackpackModel;
import com.metoo.pojo.order.vo.NrBackpackVo;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@DubboService
@Transactional
public class NrBackpackApiImpl implements NrBackpackApi {

    @Resource
    private NrBackpackService nrBackpackService;

    @Resource
    private NrGoodsService nrGoodsService;

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;
    @DubboReference
    private TjUserApi tjUserApi;

    @Override
    public RE myBackpackList(NrBackpackVo vo) {
        Page<NrBackpack> page = new Page<>(vo.getPagenum(), vo.getPagesize());
        LambdaQueryWrapper<NrBackpack> lqw = new LambdaQueryWrapper<>();
        lqw.eq(NrBackpack::getUid, vo.getUserId());
        page = nrBackpackService.page(page, lqw);
        List<NrBackpack> list = page.getRecords();
        if (OU.isBlack(list)) {
            return RE.noData();
        }
        return REPage.ok(vo.getPagenum(), vo.getPagesize(), page.getTotal(), list.stream().flatMap(e->{
            NrBackpackModel model = CopyUtils.copy(e, new NrBackpackModel());
            model.setImg(OSSUtil.fillPath(model.getImg()));
            return Stream.of(model);
        }).collect(Collectors.toList()));
    }

    @Override
    public RE buyGoods(NrGoodsVo vo) {
        TjUserAccountModel accountModel = tjUserAccountApi.findByUid(vo.getUserId());
        NrGoods goods = nrGoodsService.getById(vo.getGoodsId());
        Assert.isNull(accountModel, "没有该用户账号");
        Assert.isNull(goods, "没有该商品");
        if (accountModel.getBalance().compareTo(goods.getPrice())<0)
            throw new LoongyaException("余额不足!");
        // 修改用户余额
        tjUserAccountApi.updateBalance(goods.getPrice(),vo.getUserId());
        pushBackpack(vo.getUserId(), goods);
        return RE.ok();
    }

    private void pushBackpack(Integer userId, NrGoods goods) {
        NrBackpack nrBackpack = nrBackpackService.findFirstByUidAndGoodsId(userId, goods.getId());
        // 新增或者修改用户背包数据
        if(OU.isBlack(nrBackpack)){
            nrBackpack = new NrBackpack();
            nrBackpack.setUpdateTime(new Date());
            nrBackpack.setRemark(goods.getRemark());
            nrBackpack.setPrice(goods.getPrice());
            nrBackpack.setNum(1);
            nrBackpack.setName(goods.getName());
            nrBackpack.setCreateTime(new Date());
            nrBackpack.setContent(goods.getContent());
            nrBackpack.setGoodsId(goods.getId());
            nrBackpack.setUid(userId);
            nrBackpack.setImg(goods.getImg());
            nrBackpackService.save(nrBackpack);
        }else {
            nrBackpackService.updateGoodsNumber(userId,goods.getId());
        }
    }

    @Override
    public RE giveGoods(NrGoodsVo vo) {
        // 根据拓展id获取用户id
        RE re = tjUserApi.findUserIdByExtendId(vo.getExtendId());
        Integer targetUserId = (Integer) re.getData();
        Assert.isNull(targetUserId, "没有目标用户");
        NrGoods goods = nrGoodsService.getById(vo.getGoodsId());
        Assert.isNull(goods, "没有该商品");
        NrBackpack userBackpack = nrBackpackService.findFirstByUidAndGoodsId(vo.getUserId(), vo.getGoodsId());
        Assert.isNull(userBackpack, "您没购买该道具,请购买");
        if(userBackpack.getNum()<1){
            throw new LoongyaException("您的道具数据不足,请购买!");
        }
        // 修改目标用户道具数量
        pushBackpack(targetUserId, goods);
        // 修改自己的道具数据,如果剩余为0,删除
        if(userBackpack.getNum() > 1){
            // 执行减一操作
            nrBackpackService.updateGoodsNumDownById(userBackpack.getId());
        }else {
            // 执行删除操作
            nrBackpackService.removeById(userBackpack.getId());
        }
        return RE.ok();
    }

    @Override
    public RE buyAndGiveGoods(NrGoodsVo vo) {
        TjUserAccountModel accountModel = tjUserAccountApi.findByUid(vo.getUserId());
        // 根据拓展id获取用户id
        RE re = tjUserApi.findUserIdByExtendId(vo.getExtendId());
        Integer targetUserId = (Integer) re.getData();
        Assert.isNull(targetUserId, "没有目标用户");
        NrGoods goods = nrGoodsService.getById(vo.getGoodsId());
        Assert.isNull(accountModel, "没有该用户账号");
        Assert.isNull(goods, "没有该商品");

        if (accountModel.getBalance().compareTo(goods.getPrice()) < 0) {
            throw new LoongyaException("余额不足,请充值");
        }
        tjUserAccountApi.updateBalance(goods.getPrice(), targetUserId);
        pushBackpack(targetUserId, goods);
        return RE.ok();
    }
}
