package com.laituo.cmsFile.controller;


import com.alibaba.fastjson2.JSON;

import com.alibaba.fastjson2.JSONException;
import com.laituo.cmsFile.Vo.param.UpdateDiaryParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.service.UpdateDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("UpdateDiary")
@Validated
public class UpdateDiaryController {

    @Autowired
    private UpdateDiaryService updateDiaryService;

    @PostMapping("addUpdateDiary")
    public R addUpdateDiary(@NotNull String name, @NotNull Integer versionMax,@NotNull Integer versionMin, @NotNull MultipartFile[] files,
                            @NotNull @Pattern(regexp = "^\\[(\\d+,?)+\\]$",message = "格式不正确") String problemId,
                            @NotNull Integer permissionId) {
        try {
            UpdateDiaryParam param = new UpdateDiaryParam();
            param.setPermissionId(permissionId);
            param.setVersionMin(versionMin);
            param.setVersionMax(versionMax);
            List<Long> objects = JSON.parseArray(problemId, Long.class);
            param.setProblemId(objects);
            param.setName(name);
            return updateDiaryService.addUpdateDiary(param,files);
        }catch (Exception e){
            Throwable cause = e.getCause();
            if (cause instanceof JSONException){
                return R.fail(ResultCode.BAD,"参数有误");
            }
            e.printStackTrace();
            return R.fail(ResultCode.Error,"未知错误");
        }
    }


}
