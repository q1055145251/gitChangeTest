package com.laituo.cmsFile.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//不返回空字段
@TableName("user")
@JsonIgnoreProperties(value = {"createdDate", "updateDate","updateTimestamp","createdTimestamp","flag","schoolUserId","phone","openid"})
public class User {

    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;

    @NotNull
    private String uid;

    private String name;

    @NotNull
    private String password;

    private String phone;

    private String info;

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
