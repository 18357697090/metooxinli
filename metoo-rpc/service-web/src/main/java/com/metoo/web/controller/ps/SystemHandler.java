package com.metoo.web.controller.ps;

import com.metoo.metoo.DTO.ArticleDTO;
import com.metoo.metoo.DTO.indexDTO;
import com.metoo.metoo.System.Report;
import com.metoo.metoo.System.ReportDao;
import com.metoo.metoo.entity.Swiper;
import com.metoo.metoo.psychology.Article;
import com.metoo.metoo.psychologyDao.ArticleDao;
import com.metoo.metoo.psychologyDao.PourOutDao;
import com.metoo.metoo.psychologyDao.PsychologyConsultDao;
import com.metoo.metoo.psychologyDao.ScaleDao;
import com.metoo.metoo.repository.SwiperDao;
import io.swagger.annotations.Api;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/system")
@Api(tags={"系统接口"})
public class SystemHandler {

    @Autowired
    private SwiperDao swiperDao;
    @Autowired
    private ScaleDao scaleDao;
    @Autowired
    private PourOutDao pourOutDao;
    @Autowired
    private PsychologyConsultDao psychologyConsultDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ReportDao reportDao;
    @Autowired
    private Mapper mapper;

    //轮播图
    @RequestMapping("/swiper")
    public List<Swiper> swiper(){
        return swiperDao.findAll();
    }

    //发布轮播图
    @RequestMapping("/jhxksave")
    public void save(@RequestBody Swiper swiper){
        System.out.println(swiper);
        swiperDao.save(swiper);
    }

    @GetMapping("/index")
    public indexDTO index(){
        indexDTO indexDTO = new indexDTO();
        indexDTO.setScales(scaleDao.findScaleRand());
        List<Article> articles =articleDao.findArticleRand();
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (Article article : articles) {
            ArticleDTO articleDTO = mapper.map(article,ArticleDTO.class);
            articleDTOS.add(articleDTO);
        }
        indexDTO.setArticleDTOS(articleDTOS);
        indexDTO.setPourOuts(pourOutDao.findPourOutRand());
        indexDTO.setPsychologyConsults(psychologyConsultDao.findPsychologyConsultRand());
        return indexDTO;
    }

    //举报
    @PostMapping("/report")
    public String report(@RequestBody Report report,@RequestHeader("UID")Integer uid){
        report.setUid(uid);
        report.setState(0);
        Report report1 = reportDao.save(report);
        if(report1!=null){
            return "success";
        }else {
            return "error";
        }
    }


















}
