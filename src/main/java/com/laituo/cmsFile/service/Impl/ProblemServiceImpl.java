package com.laituo.cmsFile.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.mapper.ProblemMapper;
import com.laituo.cmsFile.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;


    @Override
    public R getProblemAll(Page<Object> objectPage) {

        


        return null;
    }
}
