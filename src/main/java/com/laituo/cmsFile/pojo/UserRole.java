package com.laituo.cmsFile.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("user_role")
@Accessors(chain = true)  //链式写法
public class UserRole {

    private Integer userId;

    private Integer roleId;

}
