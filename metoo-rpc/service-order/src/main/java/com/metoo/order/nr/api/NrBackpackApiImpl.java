package com.metoo.order.nr.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.*;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.order.NrBackpackApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserAccountDetailApi;
import com.metoo.api.tj.TjUserApi;
import com.metoo.order.nr.dao.entity.NrBackpack;
import com.metoo.order.nr.dao.entity.NrGoods;
import com.metoo.order.nr.service.NrBackpackService;
import com.metoo.order.nr.service.NrGoodsService;
import com.metoo.pojo.nr.vo.NrGoodsVo;
import com.metoo.pojo.old.vo.BackpackDTO;
import com.metoo.pojo.order.model.NrBackpackModel;
import com.metoo.pojo.order.vo.NrBackpackVo;
import com.metoo.pojo.tj.model.TjUserAccountDetailAddDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.pojo.tj.vo.TjUserAccountDetailVo;
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
    private TjUserAccountDetailApi tjUserAccountDetailApi;
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
        if(OU.isBlack(accountModel))
            return RE.fail("?????????????????????");
        if(OU.isBlack(goods))
            return RE.fail("???????????????");

        if (accountModel.getBalance().compareTo(goods.getPrice())<0)
            throw new LoongyaException("????????????!");
        // ??????????????????
        tjUserAccountApi.updateBalance(goods.getPrice(),vo.getUserId());
        // todo. need asyn
        TjUserAccountDetailAddDetailModel acModel = new TjUserAccountDetailAddDetailModel();
        acModel.setUid(vo.getUserId());
        acModel.setRemark("????????????????????????");
        acModel.setContent("????????????,??????" + goods.getPrice() + "??????");
        acModel.setPrice(goods.getPrice());
        acModel.setAccountId(accountModel.getId());
        acModel.setType(ConstantUtil.TjUserAccountDetailTypeEnum.BUY_GOODS.getCode());
        acModel.setTypeName(ConstantUtil.TjUserAccountDetailTypeEnum.BUY_GOODS.getMsg());
        acModel.setAfterPrice(accountModel.getBalance().subtract(goods.getPrice()));
        acModel.setPrePrice(accountModel.getBalance());
        tjUserAccountDetailApi.insertDetails(acModel);
        pushBackpack(vo.getUserId(), goods);
        return RE.ok();
    }

    private void pushBackpack(Integer userId, NrGoods goods) {
        NrBackpack nrBackpack = nrBackpackService.findFirstByUidAndGoodsId(userId, goods.getId());
        // ????????????????????????????????????
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
        // ????????????id????????????id
        RE re = tjUserApi.findUserIdByExtendId(vo.getExtendId());
        Integer targetUserId = (Integer) re.getData();
        if(OU.isBlack(targetUserId))
            return RE.fail("??????????????????");
        NrGoods goods = nrGoodsService.getById(vo.getGoodsId());
        if(OU.isBlack(goods))
            return RE.fail("???????????????");
        NrBackpack userBackpack = nrBackpackService.findFirstByUidAndGoodsId(vo.getUserId(), vo.getGoodsId());
        if (OU.isBlack(userBackpack))
            RE.fail("?????????????????????,?????????");
        if(userBackpack.getNum()<1){
            RE.fail("????????????????????????,?????????!");
        }
        // ??????????????????????????????
        pushBackpack(targetUserId, goods);
        // ???????????????????????????,???????????????0,??????
        if(userBackpack.getNum() > 1){
            // ??????????????????
            nrBackpackService.updateGoodsNumDownById(userBackpack.getId());
        }else {
            // ??????????????????
            nrBackpackService.removeById(userBackpack.getId());
        }
        return RE.ok();
    }

    @Override
    public RE buyAndGiveGoods(NrGoodsVo vo) {
        TjUserAccountModel accountModel = tjUserAccountApi.findByUid(vo.getUserId());
        // ????????????id????????????id
        RE re = tjUserApi.findUserIdByExtendId(vo.getExtendId());
        Integer targetUserId = (Integer) re.getData();
        if(OU.isBlack(targetUserId))
            return RE.fail("??????????????????");
        NrGoods goods = nrGoodsService.getById(vo.getGoodsId());
        if(OU.isBlack(accountModel))
            return RE.fail("?????????????????????");
        if(OU.isBlack(goods))
            return RE.fail("???????????????");

        if (accountModel.getBalance().compareTo(goods.getPrice()) < 0) {
            return RE.fail("????????????,?????????");
        }
        tjUserAccountApi.updateBalance(goods.getPrice(), targetUserId);
        // todo. need asyn
        TjUserAccountDetailAddDetailModel acModel = new TjUserAccountDetailAddDetailModel();
        acModel.setUid(vo.getUserId());
        acModel.setRemark("????????????????????????");
        acModel.setContent("????????????,??????" + goods.getPrice() + "??????" + ", ????????????id:{" + goods.getId() + "}" + "??????????????????: {" + goods.getName() + "}");
        acModel.setPrice(goods.getPrice());
        acModel.setAccountId(accountModel.getId());
        acModel.setType(ConstantUtil.TjUserAccountDetailTypeEnum.GIVE_GOODS.getCode());
        acModel.setTypeName(ConstantUtil.TjUserAccountDetailTypeEnum.GIVE_GOODS.getMsg());
        acModel.setAfterPrice(accountModel.getBalance().subtract(goods.getPrice()));
        acModel.setPrePrice(accountModel.getBalance());
        tjUserAccountDetailApi.insertDetails(acModel);
        pushBackpack(targetUserId, goods);
        return RE.ok();
    }
}
