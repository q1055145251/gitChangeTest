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
     * @param param     title--标题  text--内容  type--类型  permissionId--模块id srcId--文件id数组
     * @return
     */
    R addProblem(ProblemParam param);

    /**
     * 删除问题 53-3
     * @param id  问题id
     * @return
     */
    R delProblem(String id);

    /**
     * 修改问题     53-4
     * @param id    问题id
     * @param param title--标题  text--内容  type--类型  permissionId--模块id
     * @return
     */
    R putProblem(Long id, ProblemParam param);

    /**
     * 根据id获取问题详细信息
     * @param id id
     * @return
     */
    R getProblem(String id);
    /**
     * 获取我的问题列表 53-6
     * @param objectPage    页码
     * @param permissionId   模块id
     * @return
     */
    R getProblemMyList(Page<Problem> objectPage, Integer permissionId);
}
