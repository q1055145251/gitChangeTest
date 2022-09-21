package com.laituo.cmsFile.controller;


import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.pojo.Project;
import com.laituo.cmsFile.service.ProjectService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class PermissionController {

    @Autowired
    private ProjectService projectService;


    @PostMapping("addPermission")
    @RequiresRoles("管理员")
    public R addPermission(@RequestBody @Validated Project project){
        String path = project.getPath();
        if (path.isEmpty()||path.equals("/")){
            return R.fail(ResultCode.BAD,"路由地址不可为空");
        }
        if (path.charAt(0)=='/'){//去掉/
            project.setPath(path.substring(1));
        }
        return projectService.addPro(project);
    }









}
