package com.metoo.web.controller.ps;


import com.loongya.core.util.RE;
import com.metoo.api.ps.PsCapsuleApi;
import com.metoo.pojo.old.model.SaveCapsulePojo;
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

    @GetMapping("/mycapsule")
    public RE myCapsule(@RequestHeader("UID") Integer uid, Integer page){
        return psCapsuleApi.myCapsule(uid, page);

    }

    @GetMapping("/modifyacapsule")
    public RE modifyCapsule(Integer state,Integer capsuleId){
        return psCapsuleApi.modifyCapsule(state, capsuleId);
    }

    @GetMapping("/capsuleDetail")
    public RE capsuleDetail(Integer page){
        return psCapsuleApi.capsuleDetail(page);
    }


    @ApiOperation("发布胶囊")
    @PostMapping("/saveCapsule")
    public RE saveCapsule(@RequestBody SaveCapsulePojo saveCapsulePojo, @RequestHeader("UID")Integer uid) {
        return psCapsuleApi.saveCapsule(saveCapsulePojo, uid);

    }


    @GetMapping("/findCapsuleById")
    public RE capsule(int capsuleId,@RequestHeader("UID")Integer uid){
        return psCapsuleApi.capsule(capsuleId, uid);

    }


}
