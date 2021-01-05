package com.metoo.web.controller.in;


import com.loongya.core.util.RE;
import com.metoo.api.in.InAreaApi;
import com.metoo.pojo.in.vo.InAreaVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-07-02
 */
@RestController
public class InAreaController {


    @DubboReference
    private InAreaApi inAreaApi;

    /**
     * 区域初始化
     */
    @PostMapping("/initCoArea")
    public RE initCoArea(InAreaVo vo){
        return inAreaApi.initCoArea(vo);
    }

}
