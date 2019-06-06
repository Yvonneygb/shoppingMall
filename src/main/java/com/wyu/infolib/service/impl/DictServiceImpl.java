package com.wyu.infolib.service.impl;

import com.github.pagehelper.PageHelper;
import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.dao.DictMapper;
import com.wyu.infolib.entity.Dict;
import com.wyu.infolib.service.DictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/3 22:56
 * @Version 1.0
 */
@Service
public class DictServiceImpl implements DictService {

    @Resource
    DictMapper dictMapper;

    @Override
    public List<Dict> dictList(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize(),false,false,false);
        return dictMapper.dictList();
    }

    @Override
    public void save(Dict dict) {
        dictMapper.insertSelective(dict);
    }

    @Override
    public void updateDict(Dict dict) {
        dictMapper.updateByPrimaryKeySelective(dict);
    }

    @Override
    public void deleteDict(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        for (int i = 0; i < list.size(); i++) {
            dictMapper.deleteByPrimaryKey(Integer.valueOf(list.get(i)));
        }
    }
}
