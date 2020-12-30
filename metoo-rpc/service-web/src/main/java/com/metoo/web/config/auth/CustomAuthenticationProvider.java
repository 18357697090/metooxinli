package com.metoo.web.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loongya.core.util.RE;
import com.metoo.pojo.login.model.LoginModel;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.web.api.LoginApiImpl;
import com.metoo.web.config.SpringContextUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

public class CustomAuthenticationProvider  implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        LoginVo loginVo = new LoginVo();
        loginVo.setPassword(password);
        loginVo.setUsername(name);
        LoginApiImpl loginApiImpl = (LoginApiImpl) SpringContextUtil.getBean("loginApiImpl");
        RE re = loginApiImpl.login(loginVo);
        if(re.getCode() == 0){
            LoginModel loginModel = new ObjectMapper().convertValue(re.getData(), LoginModel.class);
            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new GrantedAuthorityImpl("ROLE_APP") );
            authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
            // 生成令牌
            Authentication auth = new UsernamePasswordAuthenticationToken(loginModel.getUserId(), loginModel.getPwd(), authorities);
            return auth;
        }else {
            throw new BadCredentialsException(re.getMsg());
        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
