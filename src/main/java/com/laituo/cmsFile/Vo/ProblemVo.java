package com.laituo.cmsFile.Vo;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.laituo.cmsFile.pojo.FileSrc;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//不返回空字段
public class ProblemVo {

    private String title;

    private String text;

    private String permissionName;

    private String userUid;

    private Long updateId;

    private Integer type;

    private Long createdTimestamp;

    private List<FileSrc> fileSrcList;

    public void setCreatedTimestamp(Integer createdTimestamp) {
        this.createdTimestamp = Long.valueOf(createdTimestamp+"000");
    }
}
