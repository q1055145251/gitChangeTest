package com.laituo.cmsFile.pojo;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//不返回空字段
@TableName("file_src")
@JsonIgnoreProperties(value = {"createdDate", "updateDate", "updateTimestamp", "createdTimestamp", "flag"})
public class FileSrc {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String path;

    private String userUid;

    private Integer flag;

    @TableField(fill = FieldFill.INSERT)
    private Date createdDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    @TableField(fill = FieldFill.INSERT)
    private Integer createdTimestamp;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateTimestamp;


    public String getPath() {
        try{
            if (path.charAt(0)=='/'){
                return "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/api" + path;
            }else {
                return path;
            }
        }catch (UnknownHostException e){
            e.printStackTrace();
            return path;
        }
    }


}
