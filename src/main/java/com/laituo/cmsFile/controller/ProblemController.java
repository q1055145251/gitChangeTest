package com.laituo.cmsFile.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.Vo.param.ProblemParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("problem")
@Validated
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("List")
    public R getProblemList(@RequestParam(defaultValue = "1") Long current,
                        @RequestParam(defaultValue = "5")Long size,
                           @RequestParam(defaultValue = "-1")Integer permissionId){
        return problemService.getProblemList(new Page<>(current,size),permissionId);
    }
    @GetMapping("myList")
    public R getProblemMyList(@RequestParam(defaultValue = "1") Long current,
                            @RequestParam(defaultValue = "5")Long size,
                            @RequestParam(defaultValue = "-1")Integer permissionId){
        return problemService.getProblemMyList(new Page<>(current,size),permissionId);
    }

    @PostMapping("add")
    public R addProblem(@RequestBody @Validated ProblemParam param){
        return problemService.addProblem(param);
    }

    @DeleteMapping("del/{id}")
    public R delProblem(@PathVariable @NotNull String id){
        return problemService.delProblem(id);
    }
//    @PutMapping("put/{id}")
//    public R putProblem(@PathVariable @NotNull Long id,@RequestBody ProblemParam param){
//        return problemService.putProblem(id,param);
//    }
    @GetMapping("/{id}")
    public R getProblem(@PathVariable String id){
        return problemService.getProblem(id);
    }




}
