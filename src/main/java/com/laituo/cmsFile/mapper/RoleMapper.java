package com.laituo.cmsFile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laituo.cmsFile.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT role.name FROM `user` INNER JOIN user_role INNER JOIN role WHERE uid=#{uid} AND user_id=user.id AND role_id=role.id")
    Set<String> getSet(String uid);
}
