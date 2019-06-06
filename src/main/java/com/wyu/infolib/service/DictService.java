package com.wyu.infolib.service;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.entity.Dict;

import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/3 22:55
 * @Version 1.0
 */
public interface DictService {
    //找到字典集合
    List<Dict> dictList(PageVO pageVO);
    //保存字典
    void save(Dict dict);
    //更新字典
    void updateDict(Dict dict);
    //批量删除
    void deleteDict(String ids);
}
