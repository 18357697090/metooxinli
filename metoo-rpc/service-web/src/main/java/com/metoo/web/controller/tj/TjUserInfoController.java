package com.metoo.web.controller.tj;


import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import com.metoo.pojo.old.vo.MeUserInfoDTO;
import com.metoo.pojo.old.vo.ModifyUserIfoDTO;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.pojo.tj.vo.TjUserInfoVo;
import com.metoo.web.config.auth.ThreadLocal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户个人信息表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/tj/tjUserInfo")
public class TjUserInfoController {

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;


    /**
     * 完善个人资料
     * @return
     */
    @PostMapping("/upLoadUserInfo")
    public RE upLoadUserInfo(TjUserInfoVo vo){
        vo.setUserId(ThreadLocal.getUserId());
        return tjUserInfoApi.upLoadUserInfo(vo);
    }


    @GetMapping("/userInfo")
    public RE userInfo(@RequestHeader("UID")Integer uid1, Integer uid){
        return tjUserInfoApi.userInfo(uid1,uid);
    }

    //修改个人信息
    @PostMapping("/modifyUserInfo")
    public RE modifyUserInfo(@RequestBody ModifyUserIfoDTO modifyUserIfoDTO, @RequestHeader("UID")Integer uid){
        return tjUserInfoApi.modifyUserInfo(uid,modifyUserIfoDTO);
    }


    //查找个人信息
    @GetMapping("/findUserInfo")
    public TjUserInfoModel findUserInfo(int uid){
        return tjUserInfoApi.findByUid(uid);
    }

    //获取个人段位
    @GetMapping("/finddw")
    public Integer finddw(Integer uid){
        TjUserInfoModel a = tjUserInfoApi.findByUid(uid);
        if(a==null){
            return -1;
        }else {
            return a.getLevel();
        }
    }



}
