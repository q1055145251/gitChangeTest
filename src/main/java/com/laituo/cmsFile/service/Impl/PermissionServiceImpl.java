package com.laituo.cmsFile.service.Impl;

import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.mapper.PermissionMapper;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.Project;
import com.laituo.cmsFile.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    @Transactional
    public boolean addPermission(List<Permission> list) {

        if (list.size()!=3){
            return false;
        }
        Integer integer = permissionMapper.insertBatchSomeColumn(list);
        if (integer==list.size()){
            return true;
        }else {
            throw new NullPointerException();
        }
    }

    @Override
    public Set<String> getSet(Integer id) {
        return permissionMapper.getSet(id);
    }


}
