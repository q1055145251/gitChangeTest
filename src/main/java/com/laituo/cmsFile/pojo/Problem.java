package com.laituo.cmsFile.pojo;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//不返回空字段
@TableName("problem")
@JsonIgnoreProperties(value = {"createdDate", "updateDate","updateTimestamp","flag"})//忽略的返回字段
public class Problem {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String text;

    private List<Long> srcId=new ArrayList<>();

    private Integer permissionId;

    public Integer getFlag() {
        if (flag==null){
            return 0;
        }
        return flag;
    }

    private String userUid;

    private Long updateId;

    @Range(min = 0,max = 2,message = "问题类型有误")
    private Integer type;
    @TableField(exist = false)//非数据库字段
    private List<FileSrc> fileSrcList;

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

    public List<Long> getSrcIdList() {
        if (srcId==null){
            return null;
        }
        return srcId;
    }

    public void setSrcIdList(List srcId) {
        this.srcId = srcId;
    }




}
