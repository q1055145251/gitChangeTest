package com.laituo.cmsFile.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laituo.cmsFile.Vo.MenuVo;
import com.laituo.cmsFile.Vo.PermissionParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.PermissionMapper;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@Service
@Slf4j
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper,Permission> implements PermissionService {

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
        permission.setFlag(0);
        try {
            if(permissionMapper.insert(permission)>0){
                permission.setFatherId(permission.getId());
                permission.setId(null);
                permission.setPath(permission.getPath()+"/write");
                permission.setPermissionCode(permission.getPermissionCode()+":write");
                permission.setPermissionName(permission.getPermissionName()+":解决问题权限");
                permission.setIsMenu(0);
                permission.setFlag(0);
                if (permissionMapper.insert(permission)>0){
                    return R.ok("成功");
                }
            }
            return R.fail(ResultCode.Error,"失败");
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
        List<MenuVo> menuVos;
        if (uid.equals("管理员")){
            menuVos=permissionMapper.getMenuListAdmin();
        }else {
            menuVos=permissionMapper.getMenuList(uid);
        }
        return menuVos;
    }

    @Override
    public List<MenuVo> getUserPermissionById(String id) {
        List<MenuVo> menuVos=permissionMapper.getUserPermissionById(id);
        Map map=new HashMap();
        for (int i = 0; i < menuVos.size(); i++) {
            if (menuVos.get(i).getIsMenu()==0){//读写权限
                MenuVo o = (MenuVo) map.get(menuVos.get(i).getPid());//找到读写权限父级
                if (o==null){//如果找不到父级，说明权限有误，将数据库该权限删除
                    permissionMapper.deleteById(menuVos.get(i).getId());
                    menuVos.remove(i--);
                    continue;
                }
                o.setWrite(true);//赋予读写权限
                menuVos.remove(i--);//去掉读写权限显示
            }else {
                map.put(menuVos.get(i).getId(),menuVos.get(i));
            }
        }
        return menuVos;
    }

    @Override
    public R getMenuTop() {
        List<Permission> permissions = permissionMapper.selectList(new QueryWrapper<Permission>().eq("father_id", 0));
        List<MenuVo> menuVos = JSON.parseArray(JSON.toJSONString(permissions), MenuVo.class);
        return R.success(menuVos);
    }

    @Override
    public Permission getPermissionWriteByPid(Integer permission_id) {//获取项目的写权限标识
        return permissionMapper.selectOne(new QueryWrapper<Permission>().eq("father_id",permission_id).last("limit 1"));
    }

    @Override
    public R getPermissionList() {
        List<Permission> permissions = permissionMapper.selectList(new QueryWrapper<Permission>()
                .eq(false,"father_id", 0)
                .eq("is_menu",1));
        List<MenuVo> menuVos = JSON.parseArray(JSON.toJSONString(permissions), MenuVo.class);
        return R.success(menuVos);
    }

    @Override
    @Transactional
    public R delPro(String id) {
        Permission permission = permissionMapper.selectById(id);

        if (Objects.isNull(permission)||permission.getIsMenu()==0){
            return R.fail(ResultCode.BAD,"id有误");
        }
        if (permission.getFatherId()==0){//顶级目录
            List<Permission> permissions = permissionMapper.selectList(new QueryWrapper<Permission>().eq("father_id", id));
            if (permissions.size()>0){
                return R.fail(ResultCode.BAD,"请先删除目录下的模块");
            }
        }
        if(permissionMapper.delete(new QueryWrapper<Permission>()
                .eq("id",permission.getId())
                .eq("father_id",permission.getId()))>0){
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

//    public List<MenuVo> permissionCopy(List<Permission> permissions){
//        List<MenuVo> menuVos=new ArrayList<>();
//        Map<Integer,MenuVo> map=new HashMap<>();
//        for (Permission permission : permissions) {
//            if (permission.getFatherId()==0){
//                MenuVo menuVo = JSON.parseObject(JSON.toJSONString(permission), MenuVo.class);
//                map.put(menuVo.getId(),menuVo);
//                menuVos.add(menuVo);
//            }else {
//                MenuVo menuVo = map.get(permission.getFatherId());
//                if (menuVo==null){//找不到父级权限，直接不添加
//                    continue;
//                }
//                if (menuVo.getChild()==null){
//                    menuVo.setChild(new ArrayList<>());
//                }
//                MenuVo menuVo1 = JSON.parseObject(JSON.toJSONString(permission), MenuVo.class);
//                menuVo.getChild().add(menuVo1);
//                map.put(menuVo1.getId(),menuVo1);
//            }
//        }
//        return menuVos;
//    }

}
