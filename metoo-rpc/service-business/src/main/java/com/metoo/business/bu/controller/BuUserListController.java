package com.metoo.business.bu.controller;


import com.metoo.api.bu.BuUserListApi;
import com.metoo.pojo.bu.model.BuUserListModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author loongya
 * @since 2021-01-06
 */
@RestController
@RequestMapping("/index")
public class BuUserListController {

    @Autowired
    private BuUserListApi userListApi;

    @PostMapping("/findAll")
    public List<BuUserListModel> findAll(Integer page){ return userListApi.findUserList(page); }

    @PostMapping("/page")
    public long page(){
        return userListApi.userCount();
    }

}
