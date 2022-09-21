package com.laituo.cmsFile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laituo.cmsFile.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT role.name FROM `user` INNER JOIN user_role INNER JOIN role WHERE id=#{id} AND user_id=id AND user_role.role_id=role.role_id")
    Set<String> getSet(Integer id);
}
