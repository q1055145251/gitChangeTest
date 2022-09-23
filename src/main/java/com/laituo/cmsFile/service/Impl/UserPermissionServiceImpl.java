package com.laituo.cmsFile.service.Impl;

import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.UserPermissionMapper;
import com.laituo.cmsFile.pojo.UserPermission;
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



    @Override
    @Transactional
    public R addUserPermission(String id,List<String> permissions) {
        List<UserPermission> userPermissionList = userPermissionMapper.checkUserPerm(id,permissions);
        if (userPermissionList.size()==0){
            return R.fail(ResultCode.BAD,"添加失败,请核对权限或用户");
        }

        Integer integer = userPermissionMapper.insertBatchSomeColumn(userPermissionList);
        if (integer==userPermissionList.size()){
            return R.ok("添加了"+integer+"条权限");
        }else {
            log.debug("添加失败，数据结构为{}",userPermissionList);
            return R.fail(ResultCode.Error,"添加失败,未知原因");
        }
    }

    @Override
    @Transactional
    public R delUserPermission(String id, List<String> permissions) {
        List<UserPermission> userPermissionList = userPermissionMapper.checkUserPerm(id,permissions);
        if (userPermissionList.size()==0){
            return R.fail(ResultCode.BAD,"删除失败,请核对权限或用户");
        }
        Integer integer = userPermissionMapper.deleteBatchIds(userPermissionList);
        if (integer==userPermissionList.size()){
            return R.ok("删除了"+integer+"条权限");
        }else {
            log.debug("删除失败，数据结构为{}",userPermissionList);
            return R.fail(ResultCode.Error,"删除失败,未知原因");
        }
    }
}
