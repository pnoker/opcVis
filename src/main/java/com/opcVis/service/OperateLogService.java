package com.opcVis.service;

import java.util.List;

import com.opcVis.model.OperateLog;
import com.opcVis.model.OperateLogExample;

public interface OperateLogService {

	void insert(OperateLog operateLog);

	List<OperateLog> getListByExample(OperateLogExample example);

}
