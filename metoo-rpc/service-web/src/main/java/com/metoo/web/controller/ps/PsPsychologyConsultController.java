package com.metoo.web.controller.ps;


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
@RequestMapping("/ps/ps-psychology-consult")
public class PsPsychologyConsultController {
    @GetMapping("/psychologyConslut")
    public List<PsychologyConsult> psychologyConsults(){
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC,"prices");
        return psychologyConsultDao.findByOnLine(1,pageable);
    }

}
