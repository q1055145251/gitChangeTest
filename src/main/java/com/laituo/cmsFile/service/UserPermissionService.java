package com.laituo.cmsFile.service;

import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.UserPermission;

import java.util.List;

public interface UserPermissionService {


    /**
     * 给用户新增权限 52-3
     * @param permissionId
     * @return
     */
    R addUserPermission(String id,Integer permissionId,boolean write);
    /**
     * 删除用户的权限 52-4
     * @param permissionId
     * @return
     */
    R delUserPermission(String id, Integer permissionId);

    /**
     * 修改用户的读写权限 52-5
     * @param permission 权限id
     * @param id        用户id
     * @param write     是否读写
     * @return
     */
    R putUserPermission(Integer permission, String id, boolean write);
}
