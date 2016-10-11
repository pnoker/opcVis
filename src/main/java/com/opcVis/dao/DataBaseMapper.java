package com.opcVis.dao;

import com.opcVis.model.DataBase;

public interface DataBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataBase record);

    int insertSelective(DataBase record);

    DataBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataBase record);

    int updateByPrimaryKey(DataBase record);
}