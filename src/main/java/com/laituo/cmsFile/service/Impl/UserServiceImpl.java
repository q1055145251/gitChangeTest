package com.laituo.cmsFile.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.UserMapper;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.Project;
import com.laituo.cmsFile.pojo.User;
import com.laituo.cmsFile.service.PermissionService;
import com.laituo.cmsFile.service.ProjectService;
import com.laituo.cmsFile.service.UserService;
import com.laituo.cmsFile.shiro.JwtToken;
import com.laituo.cmsFile.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectService projectService;

    @Override
    public R login(User user) {
        User backUser = userMapper.selectOne(new QueryWrapper<User>().eq("uid",user.getUid()));
        if (Objects.isNull(backUser)||!backUser.getPassword().equals(user.getPassword())){//身份效验失败
            return R.fail(ResultCode.UNTO_LOGIN,"账号或密码错误");
        }
        Subject subject = SecurityUtils.getSubject();//shiro登录
        String token = JwtUtils.getJwtToken(backUser.getId());
        JwtToken jwtToken = new JwtToken(token, user.getPassword());
        subject.login(jwtToken);//登录开始
        backUser.setPassword("");
        Map<String, Object> map = new HashMap<>();
        map.put("user", backUser);
        map.put("token", token);
        return R.success(map);
    }

    @Override
    public R getMenuList(Integer id) {

        Subject currentUser = SecurityUtils.getSubject();//获取角色
        List<Project> menuList= new ArrayList<>();
        if (currentUser.hasRole("管理员")) {//判断当前角色是不是管理员
             menuList = projectService.getMenuList();
        }else {
             menuList = projectService.getMenuList(id);
        }

        if (menuList.size()==0){
            return R.fail(ResultCode.BAD,"没有查找到有目录菜单");
        }

        return R.success(menuList);
    }


}
