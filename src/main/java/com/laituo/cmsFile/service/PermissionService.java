package com.laituo.cmsFile.service;

import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.Project;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    boolean addPermission(List<Permission> permission);

    Set<String> getSet(Integer id);


}
