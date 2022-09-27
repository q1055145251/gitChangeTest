package com.laituo.cmsFile.Vo.param;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//不返回空字段
public class UpdateDiaryParam {

    private String name;

    private Integer versionMin;

    private Integer versionMax;

    @NotNull
    private List<Long> problemId;

    @NotNull
    private Integer permissionId;
}
