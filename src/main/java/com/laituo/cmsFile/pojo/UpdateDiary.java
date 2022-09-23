package com.laituo.cmsFile.pojo;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//不返回空字段
@TableName("update_diary")
@JsonIgnoreProperties(value = {"createdDate", "updateDate","updateTimestamp","createdTimestamp","flag"})
public class UpdateDiary {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Float versions;
    private List<Long> srcId;

    private List<Long> problemId;

    private Integer permission_id;

    private Integer userId;

    private Integer type;

    private Integer flag;

    @TableField(fill= FieldFill.INSERT)
    private Date createdDate;

    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updateDate;

    @TableField(fill= FieldFill.INSERT)
    private Integer createdTimestamp;

    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Integer updateTimestamp;

    public void setSrcId(String srcId) {
        this.srcId = JSON.parseArray(srcId,Long.class);;
    }

    public void setProblemId(String problemId) {
        this.problemId = JSON.parseArray(problemId,Long.class);
    }
}
