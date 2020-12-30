package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsPourOutApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 心理倾诉师表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psPourOut")
public class PsPourOutController {

    @DubboReference
    private PsPourOutApi psPourOutApi;

    @GetMapping("/capsule")
    public RE capsule(){
        return psPourOutApi.capsule();

    }



}
