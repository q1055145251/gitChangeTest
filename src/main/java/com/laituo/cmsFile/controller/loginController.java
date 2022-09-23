package com.laituo.cmsFile.controller;


import com.laituo.cmsFile.Vo.RegisterUserParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.User;
import com.laituo.cmsFile.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class loginController {

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

}
