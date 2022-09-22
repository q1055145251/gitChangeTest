package com.laituo.cmsFile.service;

import com.laituo.cmsFile.Vo.MenuVo;
import com.laituo.cmsFile.Vo.PermissionParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.Project;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PermissionService {

    /**
     * 获取权限
     * @param uid
     * @return
     */
    Set<String> getSet(String uid);

    /**
     * 添加模块 50-1
     * @param permission    权限标识----permissionCode  菜单名字----permissionName 父目录----fatherId 路径----path 是否为菜单----isMenu
     * @return
     */
    R addPro(Permission permission);

    /**
     * 获取目录
     * @param uid
     * @return
     */
    List<MenuVo> getMenuList(String uid);

    /**
     * 删除模块 50-2
     * @param id 模块id
     * @return
     */
    R delPro(String id);

    /**
     * 添加权限 50-3
     * @param id 模块id
     * @return
     */
    R addPerm(String id);

    /**
     * 获取权限列表 50-4
     * @param uid 用户uid
     * @return
     */
    R getPermissionList(String uid);

    /**
     * 修改权限 模块 50-5
     * @param param permissionCode----权限唯一标识  permissionName----权限名字 fatherId----父id  path---路由
     * @return
     */
    R putPro(PermissionParam param);
}
