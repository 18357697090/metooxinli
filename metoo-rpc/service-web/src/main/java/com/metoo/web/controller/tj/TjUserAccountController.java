package com.metoo.web.controller.tj;


import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserAccountApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/tj/useraccount")
public class TjUserAccountController {

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @PostMapping("getUserAccountByUserId")
    public RE getUserAccountByUserId(Integer userId){
       return tjUserAccountApi.getUserAccountByUserId(userId);
    }

}
