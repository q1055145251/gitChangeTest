package com.laituo.cmsFile.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.Vo.param.ProblemParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.service.ProblemService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("List")
    @RequiresRoles(value = {"管理员","用户"},logical = Logical.OR)
    public R getProblemList(@RequestParam(defaultValue = "1") Long current,
                        @RequestParam(defaultValue = "5")Long size,
                           @RequestParam(defaultValue = "-1")Integer permissionId){
        return problemService.getProblemList(new Page<>(current,size),permissionId);
    }

    @PostMapping("add")
    @RequiresRoles(value = {"管理员","用户"},logical = Logical.OR)
    public R addProblem(@NotNull String title,@NotNull Integer permissionId, Integer type,
                        @NotNull String text, MultipartFile [] files){
        ProblemParam param = new ProblemParam();
        param.setTitle(title);
        param.setType(type==null?0:type);
        param.setPermissionId(permissionId);
        param.setText(text);
        return problemService.addProblem(param,files);
    }





}
