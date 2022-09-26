package com.laituo.cmsFile.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.pojo.FileSrc;
import com.laituo.cmsFile.pojo.Problem;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileSrcService {
    R getFileListAll(Page<FileSrc> objectPage);

    Map updateFiles(MultipartFile[] files);
}
