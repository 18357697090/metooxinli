package com.metoo.web.controller.in;


import com.loongya.core.util.RE;
import com.metoo.api.in.InDictApi;
import com.metoo.pojo.in.vo.InDictVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2021-01-08
 */
@RestController
@RequestMapping("/in/inDict")
public class InDictController {

    @DubboReference
    private InDictApi inDictApi;

    /**
     * 获取用户等级列表
     */
    @PostMapping("/levelList")
    public RE levelList(InDictVo vo){
        return inDictApi.levelList(vo);
    }

}
