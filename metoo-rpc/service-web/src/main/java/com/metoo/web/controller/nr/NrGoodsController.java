package com.metoo.web.controller.nr;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/nr/nr-goods")
public class NrGoodsController {

    @GetMapping("/goods")
    public List<Goods> shop(){
        return goodsDao.findAll();
    }


}
