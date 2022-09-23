package com.laituo.cmsFile.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.service.ProblemService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("problemAll")
    @RequiresRoles("管理员")
    public R getProblemAll(@RequestParam(defaultValue = "1") Long current,
                        @RequestParam(defaultValue = "5")Long size){
        return problemService.getProblemAll(new Page<>(current,size));
    }

//    @PostMapping("problem")
//    @RequiresRoles(value = {"管理员","用户"},logical = Logical.OR)
//    public R addProblem(){
//
//    }





}
