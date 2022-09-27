package com.laituo.cmsFile.Vo.param;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProblemParam {

    @NotNull
    private String title;
    @NotNull
    private Integer permissionId;
    @Range(min = 0,max = 2,message = "问题类型有误")
    private Integer type=0;
    @NotNull
    private String text;

    private List<Long> srcId;


}
