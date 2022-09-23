package com.laituo.cmsFile.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.laituo.cmsFile.common.R;
import com.laituo.cmsFile.common.ResultCode;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//不返回空字段
@TableName("permission")
@Accessors(chain = true)  //链式写法
@JsonIgnoreProperties(value = {"createdDate", "updateDate","updateTimestamp","createdTimestamp","flag"})
public class Permission {

    @TableId(value = "id",type= IdType.AUTO)
    private Integer Id;

    @NotNull
    private String permissionCode;
    @NotNull
    private String permissionName;

    private Integer fatherId=0;



    @NotNull
    private String path="/";
    @Range(min=0,max=1,message = "请输入正确的isMenu")
    private Integer isMenu=1;

    @TableLogic(value = "0",delval = "NULL")
    private Integer flag;

    @TableField(fill= FieldFill.INSERT)
    private Date createdDate;

    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updateDate;

    @TableField(fill= FieldFill.INSERT)
    private Integer createdTimestamp;

    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Integer updateTimestamp;



    public void setPath(@NotNull String path) {
        if (path.isEmpty()){
            this.path = "/";
        }
        if (path.charAt(0)!='/'){//如果第一个不是/
            this.path="/"+path;
        }
    }

}
