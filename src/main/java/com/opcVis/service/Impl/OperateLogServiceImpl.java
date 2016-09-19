package com.opcVis.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opcVis.dao.OperateLogMapper;
import com.opcVis.model.OperateLog;
import com.opcVis.model.OperateLogExample;
import com.opcVis.service.OperateLogService;
@Service("operateLogService")
public class OperateLogServiceImpl implements OperateLogService{
	@Autowired
	private OperateLogMapper operateLogMapper;

	@Override
	public void insert(OperateLog operateLog) {
		operateLogMapper.insert(operateLog);
	}

	@Override
	public List<OperateLog> getListByExample(OperateLogExample example) {
		return operateLogMapper.selectByExample(example);
	}

}
