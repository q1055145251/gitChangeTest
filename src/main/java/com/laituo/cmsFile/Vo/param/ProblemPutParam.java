package com.laituo.cmsFile.Vo.param;


import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProblemPutParam {
    private Integer permissionId;
    @Range(min = 0,max = 2,message = "问题类型有误")
    private Integer type=0;

    private String text;

    private List<Long> files;

    private MultipartFile[] filess;


}
