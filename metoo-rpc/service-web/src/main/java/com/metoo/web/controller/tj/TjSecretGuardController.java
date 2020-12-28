package com.metoo.web.controller.tj;


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
@RequestMapping("/tj/tj-secret-guard")
public class TjSecretGuardController {


    //密保问题
    @GetMapping("/findSecretGuard")
    public String findSecretGuard(String username){
        secretGuard a= secretGuardDao.findByUsername(username);
        if(a==null){
            return "asd!!@@##";
        }
        return a.getSecretGuard();
    }


}
