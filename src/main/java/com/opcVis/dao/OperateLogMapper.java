package com.opcVis.dao;

import com.opcVis.model.OperateLog;
import com.opcVis.model.OperateLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperateLogMapper {
    int countByExample(OperateLogExample example);

    int deleteByExample(OperateLogExample example);

    int insert(OperateLog record);

    int insertSelective(OperateLog record);

    List<OperateLog> selectByExample(OperateLogExample example);

    int updateByExampleSelective(@Param("record") OperateLog record, @Param("example") OperateLogExample example);

    int updateByExample(@Param("record") OperateLog record, @Param("example") OperateLogExample example);
}