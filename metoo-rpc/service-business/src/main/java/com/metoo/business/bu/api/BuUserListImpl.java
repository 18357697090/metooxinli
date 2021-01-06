package com.metoo.business.bu.api;

import com.metoo.api.bu.BuUserListApi;
import com.metoo.business.bu.dao.entity.BuUserList;
import com.metoo.business.bu.service.BuUserListService;
import com.metoo.pojo.bu.model.BuUserListModel;
import com.metoo.pojo.im.model.ImUserMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 首页轮播图 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@Transactional
@Slf4j
public class BuUserListImpl implements BuUserListApi {

    @Autowired
    private BuUserListService buUserListService;

    @Autowired
    private Mapper mapper;


    @Override
    public List<BuUserListModel> findUserList(Integer page) {
        page =page*10;
        List<BuUserList> buUserLists = buUserListService.findUserList(page);
        List<BuUserListModel> buUserListModels =buUserLists.stream().flatMap(e->{
            return Stream.of(mapper.map(e, BuUserListModel.class));
        }).collect(Collectors.toList());
        return buUserListModels;
    }

    @Override
    public long userCount() {
        return buUserListService.userCount();
    }

}
