package com.laituo.cmsFile.controller;


import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.pojo.User;
import com.laituo.cmsFile.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping( "/user/getMenuList")
    public R getMenuList() {
        Map principal = (Map) SecurityUtils.getSubject().getPrincipal();
        Integer id = (Integer) principal.get("id");
        if (id==null){
            return R.fail(ResultCode.UN_LOGIN,"登录超时或未登录");
        }
        return userService.getMenuList(id);
    }

}
