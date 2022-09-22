package com.laituo.cmsFile.service.Impl;

import com.laituo.cmsFile.mapper.RoleMapper;
import com.laituo.cmsFile.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public Set<String> getSet(String uid) {
        return roleMapper.getSet(uid);
    }


}
