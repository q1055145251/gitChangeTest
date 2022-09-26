package com.laituo.cmsFile.service.Impl;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.Vo.MenuVo;
import com.laituo.cmsFile.Vo.RegisterUserParam;
import com.laituo.cmsFile.Vo.UserPageVo;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.UserMapper;
import com.laituo.cmsFile.pojo.Project;
import com.laituo.cmsFile.pojo.User;
import com.laituo.cmsFile.service.PermissionService;
import com.laituo.cmsFile.service.ProjectService;
import com.laituo.cmsFile.service.UserRoleService;
import com.laituo.cmsFile.service.UserService;
import com.laituo.cmsFile.shiro.JwtToken;
import com.laituo.cmsFile.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public R login(User user) {
        Subject subject = SecurityUtils.getSubject();//shiro登录
        String token = JwtUtils.getJwtToken(user.getUid());
        JwtToken jwtToken = new JwtToken(token, user);
        try {
            subject.login(jwtToken);//登录开始
        } catch (UnknownAccountException e) {
            return R.fail(ResultCode.UNTO_LOGIN,"账号或密码错误");
        } catch (IncorrectCredentialsException e) {
            return R.fail(ResultCode.UNTO_LOGIN,"账号或密码错误");
        }
        User backUser=jwtToken.getUser();
        backUser.setPassword("");
        Map<String, Object> map = new HashMap<>();
        map.put("user", backUser);
        map.put("token", token);
        return R.success(map);
    }

    @Override
    public R getMenuList(String uid) {

        if (uid.isEmpty()||uid.equals("管理员")){
            return R.fail(ResultCode.FID,"非法访问");
        }
        Subject currentUser = SecurityUtils.getSubject();
        Object roleNames = currentUser.getSession().getAttribute("roleNames");
        List<MenuVo> menuList;
        if (currentUser.hasRole("管理员")){
            menuList = permissionService.getMenuList("管理员");
        }else {
            menuList = permissionService.getMenuList(uid);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("role",roleNames);
        map.put("menu",menuList);
        return R.success(map);
    }

    @Override
    @Transactional
    public R register(RegisterUserParam registerUserParam) {
        User user = JSON.parseObject(JSON.toJSONString(registerUserParam), User.class);
        if(userMapper.insert(user)>0){
            if(userRoleService.addUserRole(user.getId(),1)>0){//给用户添加普通角色
                return R.ok("注册成功");
            }
        }
        log.debug("未知的注册失败原因,注册信息{}",user);
        return R.fail(ResultCode.Error,"注册失败，未知原因");
    }

    @Override
    public R getUserList(Page<UserPageVo> page) {
        //分页，默认查询所有的记录
        Page<UserPageVo> userPageVo = userMapper.selectPageVo(page);
        return R.success(userPageVo);
    }

    @Override
    public R getUserPermissionById(String Id) {
        return R.success(permissionService.getUserPermissionById(Id));
    }

    @Override
    public R delUser(String id) {
        if(userMapper.deleteById(id)>0){
            return R.ok("删除成功");
        }else {
            return R.fail(ResultCode.Error,"删除失败。未知原因");
        }
    }


}
