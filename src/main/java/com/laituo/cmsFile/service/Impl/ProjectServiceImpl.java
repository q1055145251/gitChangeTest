package com.laituo.cmsFile.service.Impl;

import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.ProjectMapper;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.Project;
import com.laituo.cmsFile.service.PermissionService;
import com.laituo.cmsFile.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private PermissionService permissionService;

    @Override
    @Transactional
    public R addPro(Project project) {

        String path = project.getPath();
        String name = project.getName();

        List<Permission> permissions = new ArrayList<>();

        permissions.add(new Permission().setPermissionName(name).setPath("/" + path)
                .setPermissionCode(path).setFatherId(0).setIsMenu(0).setFlag(0));
        permissions.add(new Permission().setPermissionName("查看问题和下载文件").setPath("/" + path+"/look")
                .setPermissionCode(path+":look").setFatherId(0).setIsMenu(0).setFlag(0));
        permissions.add(new Permission().setPermissionName("解决问题和上传文件").setPath("/" + path+"/write")
                .setPermissionCode(path+":write").setFatherId(0).setIsMenu(0).setFlag(0));

        if(permissionService.addPermission(permissions)){
            project.setPath("/"+path);
            if (projectMapper.insert(project)>0){
                return R.ok("成功");
            }
        }
        return R.fail(ResultCode.Error,"添加失败");
    }

    @Override
    public List<Project> getMenuList(Integer id) {
        return projectMapper.getMenuList(id);
    }

    @Override
    public List<Project> getMenuList() {
        return projectMapper.getMenuList();
    }
}
