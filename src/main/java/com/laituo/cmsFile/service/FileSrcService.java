package com.laituo.cmsFile.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.FileSrc;
import org.springframework.web.multipart.MultipartFile;

public interface FileSrcService {
    R getFileListAll(Page<FileSrc> objectPage);

    R updateFiles(MultipartFile[] files);
}
