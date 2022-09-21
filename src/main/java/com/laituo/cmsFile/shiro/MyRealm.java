package com.laituo.cmsFile.shiro;


import com.laituo.cmsFile.service.PermissionService;
import com.laituo.cmsFile.service.RoleService;
import com.laituo.cmsFile.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @PackageName:com.ye.shiro.realm
 * @ClassName:MyRealm
 * @Description:
 * @author:何进业
 * @date:2021/4/30 10:58
 */
@Component
public class MyRealm extends AuthorizingRealm {


    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;


    @Override
    //获得自己定义的token
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Claims user = (Claims) principalCollection.iterator().next();//获取登录账号
        Integer id = (Integer) user.get("id");
        Set<String> roleNames = roleService.getSet(id);//查询角色
        Set<String> permission = permissionService.getSet(id);//查询权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNames);
        info.setStringPermissions(permission);
        return info;
    }

    //使用自定义token
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = (String) jwtToken.getPrincipal();
        Claims claims = JwtUtils.getIdByJwtToken(token);
        if (!JwtUtils.checkToken(token)){
            return null;
        }
        return new SimpleAuthenticationInfo(claims, claims.get("openid"), getName());
    }

//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        String username = token.getUsername();
//        User user = userService.getUserByUsername(username);
//        if (user == null){
//            return null;
//        }
//        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
//    }

}
