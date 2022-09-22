package com.laituo.cmsFile.controller;


import com.alibaba.fastjson.JSONObject;
import com.laituo.cmsFile.Vo.PermissionParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.Project;
import com.laituo.cmsFile.service.PermissionService;
import com.laituo.cmsFile.service.ProjectService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/role")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @PostMapping("pro")
    @RequiresRoles("管理员")
    public R addPro(@RequestBody @Validated Permission permission){
        return permissionService.addPro(permission);
    }

    @DeleteMapping("pro/{id}")
    @RequiresRoles("管理员")
    public R delPro(@PathVariable("id") @NotNull String id){
        return permissionService.delPro(id);
    }

    @PutMapping("pro/{id}")
    @RequiresRoles("管理员")
    public R putPro(@PathVariable("id")@NotNull Integer id, @RequestBody PermissionParam param){
        param.setId(id);
        return permissionService.putPro(param);
    }



    @PostMapping("permWrite")
    @RequiresRoles("管理员")
    public R addPerm(@RequestBody @NotNull String id){
        JSONObject parse=JSONObject.parseObject(id);
        id= (String) parse.get("id");
        return permissionService.addPerm(id);
    }

    @GetMapping("permissionList")
    @RequiresRoles(value = {"管理员","用户"},logical = Logical.OR)
    public R getPermission(){
        Map principal = (Map) SecurityUtils.getSubject().getPrincipal();
        String uid = (String) principal.get("uid");
        if (uid==null){
            return R.fail(ResultCode.UN_LOGIN,"登录超时或未登录");
        }
        return permissionService.getPermissionList(uid);
    }


}
