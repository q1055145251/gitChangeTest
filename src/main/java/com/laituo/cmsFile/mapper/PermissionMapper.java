package com.laituo.cmsFile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    Integer insertBatchSomeColumn(List entityList);

    @Select("SELECT permission_code FROM `user` INNER JOIN user_permission INNER JOIN permission WHERE id=#{id} AND id=user_id AND user_permission.permission_id=permission.permission_id AND father_id!=0")
    Set<String> getSet(Integer id);


}
