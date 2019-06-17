package com.wyu.infolib.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.dao.UserInfoMapper;
import com.wyu.infolib.entity.UserInfo;
import com.wyu.infolib.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/1 22:57
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findByAccount(String account) {
        return userInfoMapper.findByAccount(account);
    }

    @Override
    public UserInfo selectByPrimaryKey(int userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoMapper.findByOpenid(openid);
    }

    @Override
    public UserInfo findByIdCard(String idCard) {
        return userInfoMapper.findByIdCard(idCard);
    }

    @Override
    public int save(UserInfo user) {
        return userInfoMapper.insertSelective(user);
    }

    @Override
    public List<UserInfo> findListHasAccount(int flag, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(),pageVO.getPageSize(),false,false,false);
        return userInfoMapper.findListHasAccount(flag);
    }

    @Override
    public PageVO findListHasAccountPage(int flag, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
        List<UserInfo> list = userInfoMapper.findListHasAccount(flag);
        PageInfo<UserInfo> pageInfo = new PageInfo<>( list);
        PageHelper.clearPage();
        PageVO vo = new PageVO();
        vo.setRows(pageInfo.getList());
        vo.setTotal(pageInfo.getTotal());
        return vo;
    }

    @Override
    public void deleteUsers(String userIds) {
        List<String> list = Arrays.asList(userIds.split(","));
        for(int i = 0; i < list.size(); i++){
            userInfoMapper.deleteByPrimaryKey(Integer.valueOf(list.get(i)));
        }
    }

    @Override
    public void updateUser(UserInfo user) {
        userInfoMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public UserInfo findById(int userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }
}
