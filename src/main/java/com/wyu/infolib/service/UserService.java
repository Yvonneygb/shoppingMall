package com.wyu.infolib.service;

import com.github.pagehelper.Page;
import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.entity.UserInfo;

import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/1 22:58
 * @Version 1.0
 */
public interface UserService {
    //通过账号找到用户
    UserInfo findByAccount(String account);
    //通过主键找到用户
    UserInfo selectByPrimaryKey(int userId);
    //通过openid找到用户
    UserInfo findByOpenid(String openid);
    //通过卡号找到用户
    UserInfo findByIdCard(String idCard);
    //新增用户
    int save(UserInfo user);
    //找到有账号的用户集合 1有，0无
    List<UserInfo> findListHasAccount(int flag, PageVO pageVO);

    PageVO findListHasAccountPage(int flag, PageVO pageVO);
    //批量删除用户
    void deleteUsers(String userIds);
    //更新用户信息
    void updateUser(UserInfo user);
    //通过主键找到对象
    UserInfo findById(int userId);
}
