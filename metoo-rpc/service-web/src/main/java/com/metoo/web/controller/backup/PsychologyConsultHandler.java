package com.metoo.web.controller.backup;

import com.metoo.metoo.psychology.PsychologyConsult;
import com.metoo.metoo.psychologyDao.PsychologyConsultDao;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consult")
@Api(tags={"心理咨询相关接口"})
public class PsychologyConsultHandler {

    @Autowired
    private PsychologyConsultDao psychologyConsultDao;

    @GetMapping("/psychologyConslut")
    public List<PsychologyConsult> psychologyConsults(){
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC,"prices");
        return psychologyConsultDao.findByOnLine(1,pageable);
    }




}
