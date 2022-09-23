package com.laituo.cmsFile.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@TableName("user_permission")
public class UserPermission {

    @NotNull
    private Integer userId;
    @NotNull
    private Integer permissionId;

}
