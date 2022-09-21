package com.laituo.cmsFile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.User;

public interface UserService {
    R login(User user);

    R getMenuList(Integer id);
}
