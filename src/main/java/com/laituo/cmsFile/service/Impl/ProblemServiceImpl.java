package com.laituo.cmsFile.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.Vo.param.ProblemParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.ProblemMapper;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.Problem;
import com.laituo.cmsFile.service.FileSrcService;
import com.laituo.cmsFile.service.PermissionService;
import com.laituo.cmsFile.service.ProblemService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private FileSrcService fileSrcService;


    @Override
    public R getProblemList(Page<Problem> page,Integer permissionId) {
        Subject currentUser = SecurityUtils.getSubject();
        if (permissionId<0){
            if (currentUser.hasRole("管理员")){
                Page<Problem> problemPage = problemMapper.selectPage(page, null);
                return R.success(problemPage);
            }else {
                return R.fail(ResultCode.FID,"无权限");
            }
        }
        Permission permission = permissionService.getById(permissionId);
        if (permission.getIsMenu()==0){
            return R.fail(ResultCode.BAD,"模块id错误");
        }
        if (!currentUser.isPermitted(permission.getPermissionCode())&&!currentUser.hasRole("管理员")){//无权限
            return R.fail(ResultCode.FID,"无权限");
        }else {
            Page<Problem> problemPage = problemMapper.selectPage(page, new QueryWrapper<Problem>().eq("permission_id",permissionId));
            //循环变成连接


            return R.success(problemPage);
        }
    }

    @Override
    @Transactional
    public R addProblem(ProblemParam param, MultipartFile[] files) {
        Subject currentUser = SecurityUtils.getSubject();
        Map principal = (Map) currentUser.getPrincipal();
        String uid = (String) principal.get("uid");//拿到用户uid

        Permission permission = permissionService.getById(param.getPermissionId());//获取模块信息
        if (permission==null||permission.getIsMenu()==0||permission.getFatherId()==0){//判断模块是否找到或是否为模块
            return R.fail(ResultCode.BAD,"模块id有误");
        }
        Problem problem = JSON.parseObject(JSON.toJSONString(param), Problem.class);
        if (files!=null){//如果文件列表有文件，批量上传
            Map map = fileSrcService.updateFiles(files);
            problem.setSrcIdList((List) map.get("updateOk"));
        }
        problem.setUserUid(uid);
        if(problemMapper.insert(problem)>0){
            return R.ok(problem.getId().toString());
        };
        return R.fail(ResultCode.Error,"失败，未知原因");
    }

//    @Override
//    public R addProblem(ProblemParam param) {
//        Subject currentUser = SecurityUtils.getSubject();
//        Map principal = (Map) currentUser.getPrincipal();
//        String uid = (String) principal.get("uid");//拿到用户uid
//        String permission=permissionService.getPermissionWriteByPid(param.getPermission_id());
//        if (!currentUser.isPermitted(permission) && !currentUser.hasRole("管理员")){//没有权限
//            return R.fail("您没有此项目的");
//        }
//
//
//        return null;
//    }
}
