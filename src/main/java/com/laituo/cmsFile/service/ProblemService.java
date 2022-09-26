package com.laituo.cmsFile.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.Vo.param.ProblemParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.Problem;
import org.springframework.web.multipart.MultipartFile;

public interface ProblemService {
    /**
     * 获取问题列表 53-2
     * @param objectPage    页码
     * @param permissionId   模块id
     * @return
     */
    R getProblemList(Page<Problem> objectPage, Integer permissionId);

    /**
     * 提出问题 53-1
     * @param param     title--标题  text--内容  type--类型  permissionId--模块id
     * @param files     文件列表
     * @return
     */
    R addProblem(ProblemParam param, MultipartFile[] files);
}
