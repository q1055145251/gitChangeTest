package com.laituo.cmsFile.controller;


import com.laituo.cmsFile.Vo.RegisterUserParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.pojo.User;
import com.laituo.cmsFile.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody @Validated User user){
        return userService.login(user);
    }
    @GetMapping("/logout")
    public R logout() {
        SecurityUtils.getSubject().logout();
        return R.ok("退出成功");
    }
    @PostMapping("/register")
    public R register(@RequestBody @Valid RegisterUserParam registerUserParam) {
        return userService.register(registerUserParam);
    }
    @GetMapping( "/user/getMenuList")
    @RequiresRoles(value = {"管理员","用户"},logical = Logical.OR)//其中一个满足
    public R getMenuList() {
        Map principal = (Map) SecurityUtils.getSubject().getPrincipal();
        String uid = (String) principal.get("uid");
        if (uid==null){
            return R.fail(ResultCode.UN_LOGIN,"登录超时或未登录");
        }
        return userService.getMenuList(uid);
    }

}
