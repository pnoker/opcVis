package com.opcVis.dao;

import com.opcVis.model.ItemConfig;

public interface ItemConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemConfig record);

    int insertSelective(ItemConfig record);

    ItemConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemConfig record);

    int updateByPrimaryKey(ItemConfig record);
}