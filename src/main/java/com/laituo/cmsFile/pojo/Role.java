package com.laituo.cmsFile.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//不返回空字段
@TableName("role")
public class Role {
    @TableId(value = "role_id",type= IdType.AUTO)
    private Integer roleId;

    private Integer name;

}
