package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsPsychologyConsultApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 心理咨询师表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psPsychologyConsult")
public class PsPsychologyConsultController {

    @DubboReference
    private PsPsychologyConsultApi psPsychologyConsultApi;

    @GetMapping("/psychologyConslut")
    public RE psychologyConsults(){
        return psPsychologyConsultApi.psychologyConsults();

    }

}
