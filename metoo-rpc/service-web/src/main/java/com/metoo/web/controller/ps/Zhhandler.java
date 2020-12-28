package com.metoo.web.controller.ps;

import com.metoo.metoo.entity.Zh;
import com.metoo.metoo.repository.ZhDao;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/zh")
@Api(tags={"用户账户相关接口"})
public class Zhhandler {

    @Autowired
    private ZhDao zhDao;



    //查找账户余额
    @GetMapping("/findBalance")
    public BigDecimal findBalance(@RequestHeader("UID") Integer uid){
        Zh zh=zhDao.findByUid(uid);
        return zh.getBalance();
    }




}
