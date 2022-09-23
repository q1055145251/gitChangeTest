package com.laituo.cmsFile.service;

import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.UserPermission;

import java.util.List;

public interface UserPermissionService {


    /**
     * 给用户新增权限 52-3
     * @param permissions
     * @return
     */
    R addUserPermission(String id,List<String> permissions);
    /**
     * 删除用户的权限 52-4
     * @param permissions
     * @return
     */
    R delUserPermission(String id, List<String> permissions);
}
