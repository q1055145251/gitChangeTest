package com.laituo.cmsFile.Vo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//不返回空字段
@Accessors(chain = true)  //链式写法
public class MenuVo {

    private Integer id;

    private String name;

    private String code;

    private String path="/";

    private Integer isMenu;

    public void setPermissionName(String permissionName) {
        this.name = permissionName;
    }

    public void setPermissionCode(String permissionCode) {
        this.code = permissionCode;
    }

    private List<MenuVo> child;

}
