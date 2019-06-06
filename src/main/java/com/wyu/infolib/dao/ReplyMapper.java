package com.wyu.infolib.dao;

import com.wyu.infolib.entity.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);
    //通过帖子id找
    List<Reply> findByPostId(@Param("postId") Integer postId);
}