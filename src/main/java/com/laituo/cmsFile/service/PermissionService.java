package com.laituo.cmsFile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laituo.cmsFile.Vo.MenuVo;
import com.laituo.cmsFile.Vo.PermissionParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService extends IService<Permission> {

    /**
     * 获取权限
     * @param uid
     * @return
     */
    Set<String> getSet(String uid);

    /**
     * 添加模块 51-1
     * @param permission    权限标识----permissionCode  菜单名字----permissionName 父目录----fatherId 路径----path
     * @return
     */
    R addPro(Permission permission);

    /**
     * 获取目录 50-4
     * @param uid
     * @return
     */
    List<MenuVo> getMenuList(String uid);

    /**
     * 删除模块 51-2
     * @param id 模块id
     * @return
     */
    R delPro(String id);

    /**
     * 添加权限 51-3
     * @param id 模块id
     * @return
     */
    R addPerm(String id);

    /**
     * 修改权限 模块 51-4
     * @param param permissionCode----权限唯一标识  permissionName----权限名字 fatherId----父id  path---路由
     * @return
     */
    R putPro(PermissionParam param);

    /**
     * 通过用户id获取用户的权限列表  52-2
     *
     * @param id 用户id
     * @return
     */
    List<MenuVo> getUserPermissionById(String id);

    /**
     * 获取目录 51-5
     * @return
     */
    R getMenuTop();

    /**
     * 用父id 找子id
     * @param permission
     * @return
     */
    Permission getPermissionWriteByPid(Integer permission);
    /**
     * 获取模块列表 51-6
     * @return
     */
    R getPermissionList();
}
