package com.metoo.user.tj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoo.user.tj.dao.entity.TjUser;
import com.metoo.user.tj.dao.mapper.TjUserMapper;
import com.metoo.user.tj.service.TjUserService;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.sun.javafx.collections.MappingChange;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-15
 */
@Service
public class TjUserServiceImpl extends ServiceImpl<TjUserMapper, TjUser> implements TjUserService {

    @Resource
    private TjUserMapper tjUserMapper;
    @Override
    @Transactional
    public RE getList() {
        String keywords = "hh";
        //分页查询器
        Page<TjUser> page1 = new Page<>(1, 2);
        //条件查询器
        LambdaQueryWrapper<TjUser> wrapper = new LambdaQueryWrapper<>();
        //构造筛选条件
        /*
            wrapper.like(keywords！＝ｎｕｌｌ，"name",keywords);
            第一个参数：该参数是一个布尔类型，只有该参数是true时，才将like条件拼接到sql中；本例中，如果name字段不为空，则拼接name字段的like查询条件；
            第二个参数：该参数是数据库中的字段名；
            第三个参数：该参数值字段值；
         */
        //判断模糊查询是否为空，如果不为空，则拼接模糊查询条件
        wrapper.like(OU.isNotBlack(keywords), TjUser::getUsername,keywords);
        //执行分页筛选查询

        Page<TjUser> userPage = tjUserMapper.selectPage(page1, wrapper);
        //找出需要的数据，封装
        List<TjUser> records = userPage.getRecords();
        long total = userPage.getTotal();
        Map map = new HashMap();
        map.put("list", records);
        map.put("total", total);
        map.put("page", 2);


        return RE.ok(map);

    }

    @Override
    public TjUser getTjUserById(Integer id) {
        return tjUserMapper.getTjUserById(id);
    }
}
