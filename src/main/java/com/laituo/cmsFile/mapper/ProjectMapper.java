package com.laituo.cmsFile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.Project;
import com.laituo.cmsFile.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    @Select("SELECT permission_name as name,path FROM `user` INNER JOIN user_permission INNER JOIN permission WHERE id=#{id} " +
            "AND id=user_id AND user_permission.permission_id=permission.permission_id AND father_id=0")
    List<Project> getMenuList(Integer id);

    @Select("SELECT name,path FROM project")
    List<Project> getMenuList();

}
