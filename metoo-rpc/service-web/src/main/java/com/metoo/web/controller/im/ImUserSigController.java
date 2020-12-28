package com.metoo.web.controller.im;


import com.loongya.core.util.RE;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("获取用户sig")
    @GetMapping("/getusersig")
    public String getusersig(@RequestHeader("UID") Integer uid){
        UserSig userSig=userSigDao.findByUid(uid);
        String identifier = ""+uid;
        if(userSig==null){
            UserSig userSig1=new UserSig();
            String usersig= api.genSig(identifier, 30*86400);
            userSig1.setUid(uid);
            userSig1.setUsersig(usersig);
            userSigDao.save(userSig1);
            return usersig;
        }else {
            long a = userSig.getUpdatetime().getTime();
            Date date=new Date();
            long b=date.getTime()-a;
            long c= 2292000000L;
            if(b>c){
                String usersig= api.genSig(identifier, 30*86400);
                Date date1=new Date();
                userSigDao.updatausersig(usersig,date1,uid);
                return usersig;
            }else {
                return userSig.getUsersig();
            }
        }
    }



}
