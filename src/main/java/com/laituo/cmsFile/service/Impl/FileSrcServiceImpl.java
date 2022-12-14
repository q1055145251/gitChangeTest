package com.laituo.cmsFile.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import com.laituo.cmsFile.mapper.FileSrcMapper;
import com.laituo.cmsFile.pojo.FileSrc;
import com.laituo.cmsFile.pojo.Problem;
import com.laituo.cmsFile.service.FileSrcService;
import com.laituo.cmsFile.utils.UploadFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Slf4j
public class FileSrcServiceImpl implements FileSrcService {

    @Autowired
    private FileSrcMapper fileSrcMapper;


    @Override
    public R getFileListAll(Page<FileSrc> page) {
        return R.success(fileSrcMapper.selectPage(page, null));
    }

    @Override
    @Transactional
    public Map updateFiles(MultipartFile[] files) {
        Map principal = (Map) SecurityUtils.getSubject().getPrincipal();
        String uid = (String) principal.get("uid");
        List updateError = new ArrayList<String>();
        List updateOk = new ArrayList<Long>();
        for (MultipartFile file : files) {
            Map map = new HashMap();
            String fileName = UploadFileUtil.uploadFile(file, uid);
            if (!fileName.equals("false")) {//如果上传成功
                FileSrc fileSrc = new FileSrc();
                fileSrc.setPath("/upload/" + uid + "/" + fileName);
                fileSrc.setName(file.getOriginalFilename());
                fileSrc.setUserUid(uid);
                if (fileSrcMapper.insert(fileSrc) > 0) {
                    updateOk.add(fileSrc.getId());
                    continue;
                }
            }
            map.put("file", file.getOriginalFilename());
            map.put("state", "上传失败");
            updateError.add(map);
        }
        Map data = new HashMap();
        data.put("updateError", updateError);
        data.put("updateOk", updateOk);
        return data;
    }

    @Override
    public R updateFile(MultipartFile file) {
        Map principal = (Map) SecurityUtils.getSubject().getPrincipal();
        String uid = (String) principal.get("uid");
        String fileName = UploadFileUtil.uploadFile(file, uid);
        if (!fileName.equals("false")) {//如果上传成功
            FileSrc fileSrc = new FileSrc();
            fileSrc.setPath("/upload/" + uid + "/" + fileName);
            fileSrc.setName(file.getOriginalFilename());
            fileSrc.setUserUid(uid);
            if (fileSrcMapper.insert(fileSrc) > 0) {
                return R.success(fileSrc.getId());
            }
        }
        return R.fail(ResultCode.Error,"上传失败未知原因");
    }

    @Override
    public List<FileSrc> getList(List<Long> srcIdList) {
        if (srcIdList == null || srcIdList.size() == 0) {
            return null;
        }
        List<FileSrc> files = fileSrcMapper.selectList(new QueryWrapper<FileSrc>().select("id", "path").in("id", srcIdList));
        return files;
    }
}
