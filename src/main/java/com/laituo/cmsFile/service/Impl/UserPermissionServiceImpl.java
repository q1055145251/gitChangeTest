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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public R addUserPermission(String id,Integer permissionId) {
        UserPermission userPermission = userPermissionMapper.checkUserPerm(id,permissionId);
        if (userPermission==null){
            return R.fail(ResultCode.BAD,"添加失败,请核对权限或用户");
        }
        if (userPermissionMapper.insert(userPermission)>0){
            return R.ok("成功");
        }else {
            log.debug("添加失败，数据结构为{}",userPermission);
            return R.fail(ResultCode.Error,"添加失败,未知原因");
        }
    }

    @Override
    @Transactional
    public R delUserPermission(String id, Integer permissionId) {
        UserPermission userPermission = userPermissionMapper.checkUserPerm(id,permissionId);
        if (userPermission==null){
            return R.fail(ResultCode.BAD,"删除失败,请核对权限或用户");
        }
        if (userPermissionMapper.deleteById(userPermission)>0){
            return R.ok("删除成功");
        }else {
            log.debug("删除失败，数据结构为{}",userPermission);
            return R.fail(ResultCode.Error,"删除失败,未知原因");
        }
    }

    @Override
    public R putUserPermission(Integer permissionId, String id, boolean write) {
        UserPermission userPermission = userPermissionMapper.selectOne(new QueryWrapper<UserPermission>().eq("user_id", id).eq("permission_id", permissionId));
        if (userPermission==null){
            return R.fail(ResultCode.BAD,"请先获取模块权限");
        }
        Permission permission = permissionService.getPermissionWriteByPid(permissionId);//获取到读写权限的id
        if (permission==null){
            return R.fail(ResultCode.BAD,"模块id有误");
        }
        Integer permissionWriteByPid=permission.getId();

        if (write==true){
            return addUserPermission(id,permissionWriteByPid);//添加
        }else {
            return delUserPermission(id,permissionWriteByPid);//删除
        }
    }
}
