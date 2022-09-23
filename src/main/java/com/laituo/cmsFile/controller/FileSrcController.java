package com.laituo.cmsFile.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.FileSrc;
import com.laituo.cmsFile.service.FileSrcService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.File;

@RestController
@RequestMapping("fileSrc")
public class FileSrcController {

    @Autowired
    private FileSrcService fileSrcService;


    @GetMapping("FileListAll")
    @RequiresRoles("管理员")
    private R getFileListAll(@RequestParam(defaultValue = "1") Long current,
                             @RequestParam(defaultValue = "5")Long size){
        return fileSrcService.getFileListAll(new Page<>(current, size));
    }



    @PostMapping("updateFiles")
    @RequiresRoles(value = {"管理员","用户"},logical = Logical.OR)
    public R updateFiles(@RequestParam("files") MultipartFile[] files){

        return fileSrcService.updateFiles(files);

    }




}
