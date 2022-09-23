package com.laituo.cmsFile.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laituo.cmsFile.Vo.RegisterUserParam;
import com.laituo.cmsFile.Vo.UserPageVo;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.User;

public interface UserService {

    /**
     * 登录接口  50-1
     * @param user 用户名----uid  密码----password
     * @return
     */
    R login(User user);

    /**
     * 获取菜单  50-2
     * @param uid   用户uid
     * @return
     */
    R getMenuList(String uid);

    /**
     * 注册接口  50-3
     * @param registerUserParam 用户名----uid 密码----password 手机号----phone 个人信息----info 姓名----name
     * @return
     */
    R register(RegisterUserParam registerUserParam);

    /**
     * 获取用户列表 52-1
     * @return
     */
    R getUserList(Page<UserPageVo> page);

    /**
     * 通过用户id获取用户的权限列表  52-2
     * @param id    用户id
     * @return
     */
    R getUserPermissionById(String id);
}
