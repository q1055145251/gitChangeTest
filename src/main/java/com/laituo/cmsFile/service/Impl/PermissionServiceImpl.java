package com.laituo.cmsFile.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laituo.cmsFile.Vo.MenuVo;
import com.laituo.cmsFile.Vo.PermissionParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.PermissionMapper;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> getSet(String uid) {
        Set<String> map = permissionMapper.getSet(uid);//先查询用户权限
        map.addAll(permissionMapper.getRoleSet(uid));
        return map;
    }

    @Override
    @Transactional
    public R addPro(Permission permission) {

        if (permission.getFatherId()!=0){
            Permission father_id = permissionMapper.selectById(permission.getFatherId());
            if (father_id==null){//如果指定的父id
                return R.fail(ResultCode.BAD,"没有找到对应的上级目录");
            }
            if (father_id.getFatherId()!=0){//指定的父id不是一个目录
                return R.fail(ResultCode.BAD,"父目录不是一个目录");
            }
        }

        try {
            if(permissionMapper.insert(permission)>0){
                return R.ok("成功");
            }else {
                return R.fail(ResultCode.Error,"失败");
            }
        }catch (Exception e){
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException) {
                return R.fail(ResultCode.BAD,"唯一标识重复");
            }
            return R.fail(ResultCode.Error,"失败");
        }

    }

    @Override
    public List<MenuVo> getMenuList(String uid) {
        List<Permission> permissions;
        if (uid.equals("管理员")){
            permissions=permissionMapper.getMenuListAdmin();
        }else {
            permissions=permissionMapper.getMenuList(uid);
        }
        return permissionCopy(permissions);
    }

    @Override
    @Transactional
    public R delPro(String id) {
        Permission permission = permissionMapper.selectById(id);

        if (Objects.isNull(permission)){
            return R.fail(ResultCode.BAD,"id有误");
        }
        if (permission.getFatherId()==0){//顶级目录
            List<Permission> permissions = permissionMapper.selectList(new QueryWrapper<Permission>().eq("father_id", id));
            if (permissions.size()>0){
                return R.fail(ResultCode.BAD,"请先删除目录下的模块");
            }
        }
        if(permissionMapper.deleteById(id)>0){
            return R.ok("删除成功");
        }else {
            log.debug("删除模块失败！id{}",id);
            return R.fail(ResultCode.Error,"删除失败，未知原因");
        }
    }

    @Override
    @Transactional
    public R addPerm(String id) {
        Permission permission = permissionMapper.selectById(id);
        if (Objects.isNull(permission)){
            return R.fail(ResultCode.BAD,"id有误");
        }
        if (permission.getFatherId()==0) {//顶级目录
            return R.fail(ResultCode.BAD,"不支持添加整个模块");
        }
        if (permission.getIsMenu()==0){
            return R.fail(ResultCode.BAD,"不支持复加权限");
        }

        permission.setPermissionCode(permission.getPermissionCode()+":write");
        permission.setPermissionName(permission.getPermissionName()+":解决问题权限");
        permission.setPath(permission.getPath()+"/write");
        permission.setFatherId(permission.getId());//写权限的id给予父级
        permission.setIsMenu(0);//隐藏权限，没有菜单
        permission.setId(null);
        if(permissionMapper.insert(permission)>0){
            return R.ok("添加成功");
        }else{
            return R.fail(ResultCode.Error,"添加失败，未知原因");
        }
    }

    @Override
    @Transactional
    public R getPermissionList(String uid) {
        List<Permission> permissions;
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("管理员")){
            permissions=permissionMapper.getPermissionListAdmin();
        }else {
            permissions=permissionMapper.getPermissionList(uid);
        }
        return R.success(permissionCopy(permissions));
    }

    @Override
    public R putPro(PermissionParam param) {
        Permission permission = permissionMapper.selectById(param.getId());

        if (permission.getFatherId()==0||permission.getIsMenu()==0){
            param.setFatherId(0);
        }
        if (param.getFatherId()!=null){
            permission.setFatherId(param.getFatherId());
        }
        if (param.getPermissionName()!=null){
            permission.setPermissionName(permission.getPermissionName());
        }
        if (param.getPath()!=null){
            permission.setPath(param.getPath());
        }
        if (param.getPermissionCode()!=null){
            permission.setPermissionCode(param.getPermissionCode());
        }
        if (permissionMapper.updateById(permission)==1){
            return R.ok("修改成功");
        }else {
            log.debug("未知的失败原因{}",permission);
            return R.fail(ResultCode.Error,"修改失败，未知原因");
        }
    }




    public List<MenuVo> permissionCopy(List<Permission> permissions){
        List<MenuVo> menuVos=new ArrayList<>();
        Map<Integer,MenuVo> map=new HashMap<>();
        for (Permission permission : permissions) {
            if (permission.getFatherId()==0){
                MenuVo menuVo = JSON.parseObject(JSON.toJSONString(permission), MenuVo.class);
                map.put(menuVo.getId(),menuVo);
                menuVos.add(menuVo);
            }else {
                MenuVo menuVo = map.get(permission.getFatherId());
                List<MenuVo> child = menuVo.getChild();
                if (child==null){
                    child=new ArrayList<>();
                }
                MenuVo menuVo1 = JSON.parseObject(JSON.toJSONString(permission), MenuVo.class);
                child.add(menuVo1);
                menuVo.setChild(child);
                map.put(menuVo1.getId(),menuVo1);
            }
        }
        return menuVos;
    }

}
