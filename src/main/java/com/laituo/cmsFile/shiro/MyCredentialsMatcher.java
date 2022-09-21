package com.laituo.cmsFile.shiro;

import com.laituo.cmsFile.service.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyCredentialsMatcher extends SimpleCredentialsMatcher {


    //使用自定义token
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return true;
//        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
//        String inPassword = new String(jwtToken.getPassword());
//        //获得数据库中的密码
//        String uid = String.valueOf(info.getPrincipals());
//
    }
}
