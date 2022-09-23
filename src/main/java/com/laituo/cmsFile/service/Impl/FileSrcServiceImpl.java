package com.laituo.cmsFile.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.mapper.FileSrcMapper;
import com.laituo.cmsFile.pojo.FileSrc;
import com.laituo.cmsFile.service.FileSrcService;
import com.laituo.cmsFile.utils.UploadFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FileSrcServiceImpl implements FileSrcService {

    @Autowired
    private FileSrcMapper fileSrcMapper;


    @Override
    public R getFileListAll(Page<FileSrc> page) {
        return R.success(fileSrcMapper.selectPage(page,null));
    }

    @Override
    public R updateFiles(MultipartFile[] files) {

        Map principal = (Map) SecurityUtils.getSubject().getPrincipal();
        String uid = (String) principal.get("uid");
        List filesUrl=new ArrayList<String>();
        for (MultipartFile file : files) {
            filesUrl.add(UploadFileUtil.uploadFile(file,uid));
        }
        return R.success(filesUrl);
    }
}
