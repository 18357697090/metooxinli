package com.metoo.web.controller.ps;


import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsCapsuleApi;
import com.metoo.pojo.login.enums.AuthEnum;
import com.metoo.pojo.old.model.SaveCapsulePojo;
import com.metoo.pojo.ps.vo.PsCapsuleVo;
import com.metoo.web.config.auth.ThreadLocal;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/ps/psCapsule")
public class PsCapsuleController {

    @DubboReference
    private PsCapsuleApi psCapsuleApi;




    /**
     * 胶囊banner图片
     * @return
     */
    @GetMapping("/psCapsuleIndexBannerList")
    public RE psCapsuleIndexBannerList(PsCapsuleVo vo){
        return psCapsuleApi.psCapsuleIndexBannerList(vo);
    }


    /**
     * 胶囊首页-胶囊列表-随机获取三条数据
     * @return
     */
    @GetMapping("/psCapsuleIndexList")
    public RE psCapsuleIndexList(PsCapsuleVo vo){
        vo.setUserId(ThreadLocal.getUserId());
        return psCapsuleApi.psCapsuleIndexList(vo);
    }



    /**
     * 更多热门胶囊列表
     * @return
     */
    @GetMapping("/psCapsuleHostListMore")
    public RE psCapsuleHostListMore(PsCapsuleVo vo){
        vo.setUserId(ThreadLocal.getUserId());
        return psCapsuleApi.psCapsuleHostListMore(vo);
    }


    /**
     * ok
     * 我的胶囊列表
     * @param vo
     * @return
     */
    @GetMapping("/mycapsule")
    public RE myCapsule(PsCapsuleVo vo){
        vo.setUserId(ThreadLocal.getUserId());
        return psCapsuleApi.myCapsule(vo);
    }

    /**
     * ok
     * 修改胶囊状态（state ==2 删除）
     * @param state
     * @param capsuleId
     * @return
     */
    @GetMapping("/modifyacapsule")
    public RE modifyCapsule(Integer state,Integer capsuleId){
        return psCapsuleApi.modifyCapsule(state, capsuleId);
    }


    /**
     * ok
     * @param vo
     * @return
     */
    @ApiOperation("发布胶囊")
    @PostMapping("/saveCapsule")
    public RE saveCapsule(PsCapsuleVo vo) {
        vo.setUserId(ThreadLocal.getUserId());
        return psCapsuleApi.saveCapsule(vo);
    }


    /**
     * ok
     * 根据胶囊id获取胶囊详情
     * @param capsuleId
     * @return
     */
    @GetMapping("/findCapsuleDetailById")
    public RE findCapsuleDetailById(Integer capsuleId){
        return psCapsuleApi.findCapsuleDetailById(capsuleId, ThreadLocal.getUserId());
    }


}
