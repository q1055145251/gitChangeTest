package com.laituo.cmsFile.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.UserMapper;
import com.laituo.cmsFile.pojo.User;
import com.laituo.cmsFile.service.UserService;
import com.laituo.cmsFile.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MyCredentialsMatcher extends SimpleCredentialsMatcher {


    @Autowired
    private UserMapper userMapper;

    //使用自定义token
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        JwtToken jwtToken = (JwtToken) token;
        User user = jwtToken.getUser();
        if (user == null) {
            return true;
        }
        User backUser = userMapper.selectOne(new QueryWrapper<User>().eq("uid", user.getUid()));
        if (Objects.isNull(backUser) || !backUser.getPassword().equals(user.getPassword())) {//身份效验失败
            return false;
        }
        jwtToken.setUser(backUser);

        return true;
    }
}
