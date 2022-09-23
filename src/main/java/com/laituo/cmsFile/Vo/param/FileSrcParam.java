package com.laituo.cmsFile.Vo.param;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//不返回空字段
public class FileSrcParam {



    private String type;//类型

    private MultipartFile file;//文件


}
