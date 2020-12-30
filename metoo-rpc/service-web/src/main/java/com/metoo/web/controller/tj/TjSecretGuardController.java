package com.metoo.web.controller.tj;


import com.loongya.core.util.RE;
import com.metoo.api.tj.TjSecretGuardApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户密保问题 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/tj/tjSecretGuard")
public class TjSecretGuardController {

    @DubboReference
    private TjSecretGuardApi tjSecretGuardApi;

    //密保问题
    @GetMapping("/findSecretGuard")
    public RE findSecretGuard(String username){
        return tjSecretGuardApi.findSecretGuard(username);

    }


}
