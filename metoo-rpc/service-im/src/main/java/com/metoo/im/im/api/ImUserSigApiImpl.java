package com.metoo.im.im.api;


import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.im.ImUserSigApi;
import com.metoo.api.tj.TjUserApi;
import com.metoo.im.im.dao.entity.ImUserSig;
import com.metoo.im.im.service.ImUserSigService;
import com.metoo.pojo.tj.model.TjUserModel;
import com.tencentyun.TLSSigAPIv2;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 用户语音聊天权限表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class ImUserSigApiImpl implements ImUserSigApi {

    private final TLSSigAPIv2 api = new TLSSigAPIv2(
            1400421634, "60307e516ff42d72a4d260873358c43054c7f08b3a5274b8dcce12019c58d5f1");

    @Autowired
    private ImUserSigService imUserSigService;

    @DubboReference
    private TjUserApi tjUserApi;

    @Override
    public RE getusersig(Integer id) {
        TjUserModel tjUserModel = tjUserApi.getTjUser(id);
        ImUserSig userSig=imUserSigService.findByUid(id);
        String identifier = tjUserModel.getExtendId();
        if(userSig==null){
            ImUserSig userSig1=new ImUserSig();
            String usersig= api.genSig(identifier, 30*86400);
            userSig1.setUid(id);
            userSig1.setUsersig(usersig);
            imUserSigService.save(userSig1);
            if (OU.isBlack(userSig)){
                return RE.noData();
            }
            return RE.ok(userSig);
        }else {
            long a = userSig.getUpdateTime().getTime();
            Date date=new Date();
            long b=date.getTime()-a;
            long c= 2292000000L;
            if(b>c){
                String usersig= api.genSig(identifier, 30*86400);
                Date date1=new Date();
                imUserSigService.updatausersig(usersig,date1,id);
                if (OU.isBlack(userSig)){
                    return RE.noData();
                }
                return RE.ok(userSig);
            }else {
                return RE.ok(userSig.getUsersig());
            }
        }
    }
}
