package com.wyu.infolib.dao;

import com.wyu.infolib.entity.Dict;

import java.util.List;

public interface DictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);

    List<Dict> dictList();
}