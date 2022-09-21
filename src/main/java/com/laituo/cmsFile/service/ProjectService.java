package com.laituo.cmsFile.service;

import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.Project;
import com.laituo.cmsFile.pojo.User;

import java.util.List;

public interface ProjectService {
    R addPro(Project project);

    List<Project> getMenuList(Integer id);

    List<Project> getMenuList();
}
