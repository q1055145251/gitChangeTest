package com.laituo.cmsFile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laituo.cmsFile.pojo.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserPermissionMapper extends BaseMapper<UserPermission> {

    Integer insertBatchSomeColumn(List entityList);

    @Select("<script> SELECT permission.id as permission_id,user.id as user_id FROM `permission` INNER JOIN `user` WHERE " +
            "user.flag=0 and permission.flag=0 and user.id=#{id} and permission.id in" +
            " <foreach collection='permissions' item='item' open='(' separator=',' close=')'> #{item} </foreach></script>")
    List<UserPermission> checkUserPerm(String id,List<String> permissions);
}
