package com.laituo.cmsFile.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.UserPermissionMapper;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.UserPermission;
import com.laituo.cmsFile.service.PermissionService;
import com.laituo.cmsFile.service.UserPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


@Service
@Slf4j
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Autowired
    private PermissionService permissionService;


    @Override
    @Transactional
    public R addUserPermission(String id, Integer permissionId, boolean write) {
        UserPermission userPermission = userPermissionMapper.checkUserPerm(id, permissionId);

        if (userPermission == null) {
            return R.fail(ResultCode.BAD, "添加失败,请核对权限或用户");
        }

        try {
            UserPermission userPermission1 = userPermissionMapper.selectOne(new QueryWrapper<UserPermission>().eq("user_id", id).eq("permission_id", permissionId));
            if (userPermission1 == null) {//如果不存在模块，则添加
                userPermissionMapper.insert(userPermission);
            }
            Permission byId = permissionService.getById(permissionId);
            userPermission1 = userPermissionMapper.selectOne(new QueryWrapper<UserPermission>().eq("user_id", id).eq("permission_id", byId.getFatherId()));
            if (userPermission1 == null) {//如果不存在目录，则添加
                userPermission.setPermissionId(byId.getFatherId());
                userPermissionMapper.insert(userPermission);
            }
            Permission permissionWriteByPid = permissionService.getPermissionWriteByPid(permissionId);
            if (write) {//写权限
                userPermission.setPermissionId(permissionWriteByPid.getId());
                userPermissionMapper.insert(userPermission);//添加写权限
            } else {
                userPermissionMapper.delete(new QueryWrapper<UserPermission>().eq("user_id", id)
                        .eq("permission_id", permissionWriteByPid.getId()));//删除写权限
            }
            return R.ok("成功");
        }catch (Exception e){
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException) {
                return R.fail(ResultCode.BAD,"重复添加");
            }
            return R.fail(ResultCode.Error,"失败");
        }
    }

    @Override
    @Transactional
    public R delUserPermission(String id, Integer permissionId) {
        UserPermission userPermission = userPermissionMapper.checkUserPerm(id, permissionId);
        if (userPermission == null) {
            return R.fail(ResultCode.BAD, "删除失败,请核对权限或用户");
        }
        if (userPermissionMapper.delete(new QueryWrapper<UserPermission>().eq("user_id",id).eq("permission_id",permissionId)) > 0) {
            return R.ok("删除成功");
        } else {
            log.debug("删除失败，数据结构为{}", userPermission);
            return R.fail(ResultCode.Error, "删除失败,未知原因");
        }
    }

    @Override
    public R putUserPermission(Integer permissionId, String id, boolean write) {
        UserPermission userPermission = userPermissionMapper.selectOne(new QueryWrapper<UserPermission>().eq("user_id", id).eq("permission_id", permissionId));
        if (userPermission == null) {
            return R.fail(ResultCode.BAD, "请先获取模块权限");
        }
        Permission permission = permissionService.getPermissionWriteByPid(permissionId);//获取到读写权限的id
        if (permission == null) {
            return R.fail(ResultCode.BAD, "模块id有误");
        }
        Integer permissionWriteByPid = permission.getId();

        if (write == true) {
            return addUserPermission(id, permissionWriteByPid, false);//添加
        } else {
            return delUserPermission(id, permissionWriteByPid);//删除
        }
    }
}
