package com.laituo.cmsFile.Vo;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class PermissionParam {

    private Integer id;

    private String permissionCode;

    private String permissionName;

    private Integer fatherId;

    private String path;


}
