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
import java.text.SimpleDateFormat;
import java.util.Date;
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
                return "false";
            }
            // 获取文件的名称
            String fileName = multipartFile.getOriginalFilename();
            // 文件后缀 例如：.png
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
            // uuid 生成文件名
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd`HH`mm`ss==");
            String format = simpleDateFormat.format(new Date());
            fileName=format+fileName;//给文件名添加时间
            // 根路径，在 resources/static/upload
            if (StringUtils.isEmpty(dir)){
                return "获取用户名失败";
            }
            String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/" + dir + "/";
            basePath = URLDecoder.decode(basePath, "UTF-8");
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
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    /**
     * 将临时目录的文件移动到正确目录
     * @param fileName 文件名
     * @param dir 用户名
     * @return
     */
    public static boolean copyFile(String fileName,String dir,String newFileName){

        try {
            if (StringUtils.isEmpty(fileName)) {
                return false;
            }
            if (StringUtils.isEmpty(dir)){
                return false;
            }
            String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/temp/" + dir + "/";
            basePath = URLDecoder.decode(basePath, "UTF-8");
            // 寻找临时文件
            File oldName = new File(basePath+fileName);
            // 文件不存在，失败
            if (!oldName.exists()) {
                return false;
            }
            if (oldName.isDirectory()) {
                return false;
            }
            String newBasePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/" + dir + "/";
            File newName = new File(newBasePath+newFileName);
            String pFile = newName.getParent();
            File pDir = new File(pFile);
            if (!pDir.exists()) {//父目录不存在，创建
                pDir.mkdirs();
            }
            return oldName.renameTo(newName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




}

