package com.laituo.cmsFile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    Integer insertBatchSomeColumn(List entityList);

    @Select("SELECT permission_code FROM user INNER JOIN permission INNER JOIN user_permission WHERE user.uid=#{uid}" +
            " AND user.id=user_id AND permission_id=permission.id AND permission.flag=0")
    Set<String> getSet(String uid);


    @Select("SELECT permission_code FROM `user` INNER JOIN user_role INNER JOIN role_permission INNER JOIN permission WHERE user.uid=#{uid} " +
            "AND user_role.role_id=role_permission.role_id AND user.id=user_id AND permission_id=permission.id AND permission.flag=0")
    Set<String> getRoleSet(String uid);

    @Select("SELECT id,permission_name,father_id,path FROM `permission` WHERE is_menu=1 AND permission.flag=0")
    List<Permission> getMenuListAdmin();

    @Select("SELECT permission.id,permission_name,path,father_id FROM `user` INNER JOIN user_permission INNER JOIN permission WHERE uid=#{uid}" +
            " AND user.id=user_id AND permission_id=permission.id AND is_menu=1 AND permission.flag=0")
    List<Permission> getMenuList(String uid);

    @Select("SELECT id,permission_code,permission_name,father_id,path,is_menu FROM `permission` WHERE permission.flag=0")
    List<Permission> getPermissionListAdmin();

    @Select("SELECT permission.id,permission_name,path,father_id,is_menu FROM `user` INNER JOIN user_permission INNER JOIN permission WHERE uid=#{uid}" +
            " AND user.id=user_id AND permission_id=permission.id AND permission.flag=0")
    List<Permission> getPermissionList(String uid);










}
