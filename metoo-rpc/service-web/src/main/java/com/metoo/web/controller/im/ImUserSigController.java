package com.metoo.web.controller.im;


import com.loongya.core.util.RE;
import com.metoo.api.im.ImUserSigApi;
import com.metoo.web.config.auth.ThreadLocal;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/getusersig")
    public RE getusersig(){
     return imUserSigApi.getusersig(ThreadLocal.getUserId());
    }



}
