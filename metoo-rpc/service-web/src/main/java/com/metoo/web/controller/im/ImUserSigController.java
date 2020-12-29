package com.metoo.web.controller.im;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImUserSigApi;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户语音聊天权限表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/im/im-user-sig")
public class ImUserSigController {

    @DubboReference
    private ImUserSigApi imUserSigApi;

    @ApiOperation("获取用户sig")
    @GetMapping("/getusersig")
    public RE getusersig(@RequestHeader("UID") Integer uid){
     return imUserSigApi.getusersig(uid);
    }



}
