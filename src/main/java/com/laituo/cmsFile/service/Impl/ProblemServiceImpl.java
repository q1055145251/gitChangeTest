package com.laituo.cmsFile.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.Vo.MenuVo;
import com.laituo.cmsFile.Vo.ProblemVo;
import com.laituo.cmsFile.Vo.param.ProblemParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.ProblemMapper;
import com.laituo.cmsFile.pojo.FileSrc;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.Problem;
import com.laituo.cmsFile.service.FileSrcService;
import com.laituo.cmsFile.service.PermissionService;
import com.laituo.cmsFile.service.ProblemService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
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
    public R getProblemList(Page<Problem> page, Integer permissionId) {

        Subject currentUser = SecurityUtils.getSubject();
        Page<Problem> problemPage = null;
        if (permissionId < 0) {
            if (currentUser.hasRole("?????????")) {
                problemPage = problemMapper.selectPage(page, null);
            } else {
                return R.fail(ResultCode.FID, "?????????");
            }
        } else {
            Permission permission = permissionService.getById(permissionId);
            if (permission.getIsMenu() == 0) {
                return R.fail(ResultCode.BAD, "??????id??????");
            }
            if (!currentUser.isPermitted(permission.getPermissionCode()) && !currentUser.hasRole("?????????")) {//?????????
                return R.fail(ResultCode.FID, "?????????");
            } else {
                problemPage = problemMapper.selectPage(page, new QueryWrapper<Problem>().eq("permission_id", permissionId));
            }
        }
        //?????????VO
        if (problemPage == null) {
            return R.success(null);
        }
        Map menuName = getMenuName();
        IPage<Object> convertPage = problemPage.convert(result -> {//??????????????????
            ProblemVo problemVo = JSON.parseObject(JSON.toJSONString(result), ProblemVo.class);//???????????????
            problemVo.setPermissionName((String) menuName.get(result.getPermissionId()));
            return problemVo;
        });
        return R.success(convertPage);
    }

    @Override
    @Transactional
    public R addProblem(ProblemParam param) {
        Subject currentUser = SecurityUtils.getSubject();
        Map principal = (Map) currentUser.getPrincipal();
        String uid = (String) principal.get("uid");//????????????uid

        Permission permission = permissionService.getById(param.getPermissionId());//??????????????????
        if (permission == null || permission.getIsMenu() == 0 || permission.getFatherId() == 0) {//??????????????????????????????????????????
            return R.fail(ResultCode.BAD, "??????id??????");
        }
        Problem problem = JSON.parseObject(JSON.toJSONString(param), Problem.class);
        problem.setUserUid(uid);
        if (problemMapper.insert(problem) > 0) {
            return R.ok(problem.getId().toString());
        }
        return R.fail(ResultCode.Error, "?????????????????????");
    }

    @Override
    public R delProblem(String id) {
        Subject currentUser = SecurityUtils.getSubject();
        Map principal = (Map) currentUser.getPrincipal();
        String uid = (String) principal.get("uid");//????????????uid
        if (!currentUser.hasRole("?????????")) {//??????????????????????????????
            Problem problem = problemMapper.selectById(id);
            if (problem == null) {//???????????????????????????
                return R.fail(ResultCode.BAD, "id??????");
            }
            if (!problem.getUserUid().equals(uid)) {//?????????????????????????????????
                return R.fail(ResultCode.FID, "?????????");
            }
        }//??????????????????????????????????????????
        if (problemMapper.deleteById(id) > 0) {
            return R.ok("????????????");
        } else {
            return R.fail(ResultCode.Error, "????????????");
        }

    }

    @Override
    public R putProblem(Long id, ProblemParam param) {
        Subject currentUser = SecurityUtils.getSubject();
        Map principal = (Map) currentUser.getPrincipal();
        String uid = (String) principal.get("uid");//????????????uid
        if (!currentUser.hasRole("?????????")) {//?????????????????????
            Problem problem = problemMapper.selectById(id);
            if (problem == null) {
                return R.fail(ResultCode.BAD, "id??????");
            }
            if (!problem.getUserUid().equals(uid)) {//?????????????????????????????????
                return R.fail(ResultCode.FID, "?????????");
            }
        }//??????????????????????????????????????????
        Permission permission = permissionService.getById(param.getPermissionId());//??????????????????
        if (permission == null || permission.getIsMenu() == 0 || permission.getFatherId() == 0) {//??????????????????????????????????????????
            return R.fail(ResultCode.BAD, "??????id??????");
        }

        Problem problem = JSON.parseObject(JSON.toJSONString(param), Problem.class);
        problem.setId(id);
        if (problemMapper.updateById(problem) > 0) {
            return R.ok("????????????");
        } else {
            return R.fail(ResultCode.Error, "????????????");
        }


    }

    @Override
    public R getProblem(String id) {
        Map menuName = getMenuName();
        Problem problem = problemMapper.selectById(id);
        ProblemVo problemVo = JSON.parseObject(JSON.toJSONString(problem), ProblemVo.class);//???????????????
        problemVo.setPermissionName((String) menuName.get(problem.getPermissionId()));
        List<FileSrc> files = fileSrcService.getList(problem.getSrcIdList());//??????????????????
        if (files != null && files.size() != 0) {
            problemVo.setFileSrcList(files);//???????????????
        }
        return R.success(problemVo);
    }

    @Override
    public R getProblemMyList(Page<Problem> page, Integer permissionId) {
        Subject currentUser = SecurityUtils.getSubject();
        Map principal = (Map) currentUser.getPrincipal();
        String uid = (String) principal.get("uid");//????????????uid
        Page<Problem> problemPage = null;
        if (permissionId < 0) {
            problemPage = problemMapper.selectPage(page, new QueryWrapper<Problem>().eq("user_uid", uid));//????????????uid??????
        } else {
            problemPage = problemMapper.selectPage(page, new QueryWrapper<Problem>().eq("permission_id", permissionId).eq("user_uid", uid));
        }
        //?????????VO
        if (problemPage == null) {
            return R.success(null);
        }
        Map menuName = getMenuName();
        IPage<Object> convertPage = problemPage.convert(result -> {//??????????????????
            ProblemVo problemVo = JSON.parseObject(JSON.toJSONString(result), ProblemVo.class);//???????????????
            problemVo.setPermissionName((String) menuName.get(result.getPermissionId()));
            return problemVo;
        });
        return R.success(convertPage);
    }

    public Map getMenuName() {
        List<MenuVo> menuVos = permissionService.getMenuList("?????????");
        Map menuName = new HashMap();
        Map temp = new HashMap();
        for (MenuVo menuVo : menuVos) {
            if (menuVo.getPid() == 0) {
                temp.put(menuVo.getId(), menuVo.getName());
            } else {
                String o = (String) temp.get(menuVo.getPid());
                if (o == null) {
                    continue;
                }
                menuName.put(menuVo.getId(), o + ">" + menuVo.getName());
            }
        }
        return menuName;
    }
}


//        IPage<Object> convertPage = problemPage.convert(result -> {//??????????????????
//            ProblemVo problemVo = JSON.parseObject(JSON.toJSONString(result), ProblemVo.class);//???????????????
//            List<FileSrc> files = fileSrcService.getList(result.getSrcIdList());//??????????????????
//            if (files != null && files.size() != 0) {
//                problemVo.setFileSrcList(files);//???????????????
//            }
//            return problemVo;
//        });
//        return R.success(convertPage);

//    @Override
//    public R addProblem(ProblemParam param) {
//        Subject currentUser = SecurityUtils.getSubject();
//        Map principal = (Map) currentUser.getPrincipal();
//        String uid = (String) principal.get("uid");//????????????uid
//        String permission=permissionService.getPermissionWriteByPid(param.getPermission_id());
//        if (!currentUser.isPermitted(permission) && !currentUser.hasRole("?????????")){//????????????
//            return R.fail("?????????????????????");
//        }
//
//
//        return null;
//    }
