package com.wyu.infolib.service;

import com.wyu.infolib.entity.ReaderComments;
import com.wyu.infolib.entity.Reply;

import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/2 9:40
 * @Version 1.0
 */
public interface ReplyService {
    //通过id找到
    Reply findById(int postId);
    //通过帖子id找到
    List<Reply> findByPostId(Integer id);
    //保存
    void save(Reply reply);
}
