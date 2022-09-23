package com.laituo.cmsFile.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.util.UUID;

/**
 * 文件上传
 */
@Component
public class UploadFileUtil {

    /**
     * 项目端口
     */
    @Value("${server.port}")
    public static String port;

    /**
     * 项目路径
     */
    @Value("${server.servlet.context-path}")
    public static String contextPath;

    @Autowired
    private Environment env;

    @PostConstruct
    public void config() {
        port = env.getProperty("server.port");
    }


    /**
     * 上传文件
     *
     * @param multipartFile 文件对象
     * @param dir           上传目录
     * @return
     */
    public static String uploadFile(MultipartFile multipartFile, String dir) {
        try {
            if (multipartFile.isEmpty()) {
                return "请选择文件";
            }
            // 获取文件的名称
            String originalFilename = multipartFile.getOriginalFilename();
            // 文件后缀 例如：.png
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // uuid 生成文件名
//            String uuid = String.valueOf(UUID.randomUUID());
            // 根路径，在 resources/static/upload
            String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/" + (StringUtils.isNotBlank(dir) ? (dir + "/") : "");
            basePath = URLDecoder.decode(basePath, "UTF-8");
            // 新的文件名，使用uuid生成文件名
            String fileName = originalFilename;
            // 创建新的文件
            File fileExist = new File(basePath);
            // 文件夹不存在，则新建
            if (!fileExist.exists()) {
                fileExist.mkdirs();
            }
            // 获取文件对象
            File file = new File(basePath, fileName);
            // 完成文件的上传
            multipartFile.transferTo(file);
            // 返回绝对路径
            return "上传成功,http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/static/upload/" + (StringUtils.isNotBlank(dir) ? (dir + "/") : "") + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}

