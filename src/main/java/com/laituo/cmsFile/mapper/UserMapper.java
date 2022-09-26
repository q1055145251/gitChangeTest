package com.laituo.cmsFile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.Vo.UserPageVo;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("SELECT user.id,uid,user.name,phone,info,role.name as role FROM `user` INNER JOIN user_role INNER JOIN role WHERE user_id=user.id AND role_id=role.id AND role.id=1 AND flag=0")
    Page<UserPageVo> selectPageVo(Page<?> page);
}
