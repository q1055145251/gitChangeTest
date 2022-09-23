package com.laituo.cmsFile.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.common.R;

public interface ProblemService {
    R getProblemAll(Page<Object> objectPage);
}
