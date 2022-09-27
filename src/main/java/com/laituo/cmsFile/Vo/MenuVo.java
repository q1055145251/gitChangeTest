package com.laituo.cmsFile.Vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

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

    private Integer pid;

    private boolean write;

    public void setFatherId(Integer fatherId) {
        this.pid = fatherId;
    }

    public void setPermissionName(String permissionName) {
        this.name = permissionName;
    }
}
