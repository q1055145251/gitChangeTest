package com.laituo.cmsFile.service.Impl;

import com.laituo.cmsFile.mapper.UserRoleMapper;
import com.laituo.cmsFile.pojo.UserRole;
import com.laituo.cmsFile.service.UserRoleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    @Transactional
    public Integer addUserRole(Integer user_id,Integer role_id) {
        return userRoleMapper.insert(new UserRole().setUserId(user_id).setRoleId(role_id));
    }
}
