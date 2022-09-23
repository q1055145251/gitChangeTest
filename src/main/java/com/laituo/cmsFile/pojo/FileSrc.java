package com.laituo.cmsFile.pojo;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//不返回空字段
@TableName("file_src")
@JsonIgnoreProperties(value = {"createdDate", "updateDate","updateTimestamp","createdTimestamp","flag"})
public class FileSrc {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String type;

    private String path;

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



}
