package com.metoo.user.tj.api;

import com.loongya.core.util.RE;
import com.metoo.api.tj.TjSecretGuardApi;
import com.metoo.user.tj.dao.entity.TjSecretGuard;
import com.metoo.user.tj.service.TjSecretGuardService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户密保问题 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class TjSecretGuardApiImpl implements TjSecretGuardApi {

    @Autowired
    private TjSecretGuardService tjSecretGuardService;

    @Override
    public RE findSecretGuard(String username) {

        TjSecretGuard a= tjSecretGuardService.findByUsername(username);
        if(a==null){
            return RE.ok("asd!!@@##");
        }
        return RE.ok(a.getSecretGuard());
    }
}
