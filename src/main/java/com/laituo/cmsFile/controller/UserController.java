package com.laituo.cmsFile.controller;


import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.Vo.RegisterUserParam;
import com.laituo.cmsFile.Vo.UserPageVo;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.pojo.User;
import com.laituo.cmsFile.pojo.UserPermission;
import com.laituo.cmsFile.service.UserPermissionService;
import com.laituo.cmsFile.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPermissionService userPermissionService;


    @GetMapping( "getMenuList")
    @RequiresRoles(value = {"管理员","用户"},logical = Logical.OR)//其中一个满足
    public R getMenuList() {
        Map principal = (Map) SecurityUtils.getSubject().getPrincipal();
        String uid = (String) principal.get("uid");
        if (uid==null){
            return R.fail(ResultCode.UN_LOGIN,"登录超时或未登录");
        }
        return userService.getMenuList(uid);
    }

    @GetMapping("getUserList")
    @RequiresRoles("管理员")
    public R getUserList(@RequestParam(defaultValue = "1") Long current,
                         @RequestParam(defaultValue = "5")Long size){
        return userService.getUserList(new Page<>(current, size));
    }


    @GetMapping("getUserPermission/{id}")
    @RequiresRoles("管理员")
    public R getUserPermissionById(@PathVariable String id){
        return userService.getUserPermissionById(id);
    }

    @PostMapping("UserPermission/{id}")
    @RequiresRoles("管理员")
    public R addUserPermission(@RequestBody @NotNull String param, @PathVariable @NotNull String id){
        Map parse = (Map) JSON.parse(param);
        Integer permissionId= (Integer) parse.get("permissionId");
        boolean write = parse.get("write")==null ? false: (boolean) parse.get("write");
        return  userPermissionService.addUserPermission(id,permissionId,write);
    }

    @DeleteMapping("UserPermission/{id}")
    @RequiresRoles("管理员")
    public R delUserPermission(@RequestBody @NotNull String param, @PathVariable @NotNull String id){
        Map parse = (Map) JSON.parse(param);
        Integer permissionId= (Integer) parse.get("permissionId");
        return  userPermissionService.delUserPermission(id,permissionId);
    }
    @PutMapping("UserPermission/{id}")
    @RequiresRoles("管理员")
    public R putUserPermission(@RequestBody @NotNull String param, @PathVariable @NotNull String id){
        Map parse = (Map) JSON.parse(param);
        Integer permissionId= (Integer) parse.get("permissionId");
        boolean write = parse.get("write")==null ? false: (boolean) parse.get("write");
        return userPermissionService.putUserPermission(permissionId,id,write);
    }

    @DeleteMapping("del/{id}")
    @RequiresRoles("管理员")
    public R delUser(@PathVariable String id){
        return userService.delUser(id);
    }




}
