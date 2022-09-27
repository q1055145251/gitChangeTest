package com.laituo.cmsFile.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laituo.cmsFile.Vo.param.UpdateDiaryParam;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.UpdateDiaryMapper;
import com.laituo.cmsFile.pojo.Permission;
import com.laituo.cmsFile.pojo.Problem;
import com.laituo.cmsFile.pojo.UpdateDiary;
import com.laituo.cmsFile.service.FileSrcService;
import com.laituo.cmsFile.service.PermissionService;
import com.laituo.cmsFile.service.UpdateDiaryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UpdateDiaryServiceImpl implements UpdateDiaryService {

    @Autowired
    private UpdateDiaryMapper updateDiaryMapper;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private FileSrcService fileSrcService;

    @Override
    public R addUpdateDiary(UpdateDiaryParam param,MultipartFile[] files) {
        Subject currentUser = SecurityUtils.getSubject();
        Map principal = (Map) currentUser.getPrincipal();
        String uid = (String) principal.get("uid");//拿到用户uid
        Permission byId = permissionService.getById(param.getPermissionId());
        if (byId==null||byId.getFatherId() == 0 || byId.getIsMenu() == 0) {
            return R.fail(ResultCode.BAD, "模块id错误");
        }
        if (files == null || files.length == 0) {
            return R.fail(ResultCode.BAD, "请选择要上传的文件");
        }
        UpdateDiary name = updateDiaryMapper.selectOne(new QueryWrapper<UpdateDiary>().select("max(id)","version_max","version_min").eq("name", param.getName()));
        return R.success(name);

//        UpdateDiary updateDiary = JSON.parseObject(JSON.toJSONString(param), UpdateDiary.class);
//        //上传文件
//        Map map = fileSrcService.updateFiles(files);
//        updateDiary.setSrcIdList((List) map.get("updateOk"));
//        updateDiary.setUserUid(uid);
//        if (updateDiaryMapper.insert(updateDiary) > 0) {
//            return R.ok(updateDiary.getId().toString());
//        }
//        return R.fail(ResultCode.Error, "失败，未知原因");
    }
}
