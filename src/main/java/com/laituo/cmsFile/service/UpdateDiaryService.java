package com.laituo.cmsFile.service;

import com.laituo.cmsFile.Vo.param.UpdateDiaryParam;
import com.laituo.cmsFile.common.R;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateDiaryService {
    R addUpdateDiary(UpdateDiaryParam param, MultipartFile[] files);
}
