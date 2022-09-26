package com.laituo.cmsFile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laituo.cmsFile.Vo.param.ProblemParam;
import com.laituo.cmsFile.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProblemMapper extends BaseMapper<Problem> {

}
