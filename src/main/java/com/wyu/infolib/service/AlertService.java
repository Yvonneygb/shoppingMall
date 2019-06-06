package com.wyu.infolib.service;

import com.wyu.infolib.entity.Alerts;

import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/1 23:24
 * @Version 1.0
 */
public interface AlertService {
    //选择性插入数据
    int insertSelective(Alerts alerts);

    //wx拿到信息list
    List<Alerts> getList(int askUserId);

    //通过id找到对象
    Alerts findById(int alertId);
    //更新信息
    int update(Alerts alerts);
    //通过askId和类型找到消息提醒
    Alerts findByAskIdR(int askId, int type);
}
