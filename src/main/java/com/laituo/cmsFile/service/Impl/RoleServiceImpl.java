package com.laituo.cmsFile.service.Impl;

import com.laituo.cmsFile.mapper.RoleMapper;
import com.laituo.cmsFile.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public Set<String> getSet(Integer id) {
        return roleMapper.getSet(id);
    }
}
