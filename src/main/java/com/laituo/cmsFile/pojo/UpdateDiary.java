package com.laituo.cmsFile.pojo;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
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

    private Integer versionMin;

    private Integer versionMax;
    private List<Long> srcId=new ArrayList<>();

    private List<Long> problemId;

    private Integer permissionId;

    private String userUid;

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
        if (srcId==null){
            return;
        }
        this.srcId = JSON.parseArray(srcId,Long.class);
    }
    public String getSrcId() {
        if (srcId==null){
            return null;
        }
        return JSON.toJSONString(srcId);
    }
    public void setSrcIdList(List srcId) {
        this.srcId = srcId;
    }
    public List<Long> getSrcIdList() {
        if (srcId==null){
            return null;
        }
        return srcId;
    }


    public void setProblemId(String problemId) {
        if (problemId==null){
            return;
        }
        this.problemId = JSON.parseArray(problemId,Long.class);
    }


    public String getProblemId() {
        if (problemId==null){
            return null;
        }
        return JSON.toJSONString(problemId);
    }
}
