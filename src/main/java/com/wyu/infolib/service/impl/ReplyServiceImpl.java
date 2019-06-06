package com.wyu.infolib.service.impl;

import com.wyu.infolib.dao.ReplyMapper;
import com.wyu.infolib.entity.Reply;
import com.wyu.infolib.service.ReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/2 9:40
 * @Version 1.0
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Resource
    ReplyMapper replyMapper;

    @Override
    public Reply findById(int postId) {
        return replyMapper.selectByPrimaryKey(postId);
    }

    @Override
    public List<Reply> findByPostId(Integer postId) {
        return replyMapper.findByPostId(postId);
    }

    @Override
    public void save(Reply reply) {
        replyMapper.insertSelective(reply);
    }
}
