package com.laituo.cmsFile.Vo.param;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProblemParam {

    @NotNull
    private String title;
    @NotNull
    private Integer permissionId;

    private Integer type=0;
    @NotNull
    private String text;

    private List<Long> srcId=new ArrayList<>();


}
