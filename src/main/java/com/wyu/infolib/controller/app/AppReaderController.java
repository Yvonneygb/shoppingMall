package com.wyu.infolib.controller.app;

import com.wyu.infolib.common.entity.ReaderAskPageVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.dao.AlertsMapper;
import com.wyu.infolib.dao.UserInfoMapper;
import com.wyu.infolib.entity.Alerts;
import com.wyu.infolib.entity.ReaderComments;
import com.wyu.infolib.entity.UserInfo;
import com.wyu.infolib.service.AlertService;
import com.wyu.infolib.service.ReaderService;
import com.wyu.infolib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * @Description 提问相关接口类
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/30 10:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/app/reader")
public class AppReaderController {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private UserService userService;

    @Autowired
    private AlertService alertService;

    //拿到读者提问数据的分页
    @GetMapping("/getAskPage")
    @ResponseBody
    public ResponseVO getAskPage(ReaderAskPageVO readerAskPageVO){
        ResponseVO responseVO = new ResponseVO();
        if(readerAskPageVO.getIsShow() == -1){
            readerAskPageVO.setIsShow(1);
        }
        responseVO.setEcode(0);
        responseVO.setEmsg("获取成功");
        responseVO.setData(readerService.getListPage(readerAskPageVO));
        return responseVO;
    }

    //对提问做出回复
    @PostMapping("/replyAsk")
    @ResponseBody
    public ResponseVO replyAsk(int askId, int msgReplyId, String msgReplyContent){
        ResponseVO responseVO = new ResponseVO();
        ReaderComments readerComments = readerService.findById(askId);
        UserInfo userInfo = userService.selectByPrimaryKey(msgReplyId);
        if(readerComments == null || userInfo == null){
            responseVO.setEcode(1);
            responseVO.setEmsg("回复失败");
            return responseVO;
        }
        readerComments.setMsgReply(userInfo.getNickname());//用户昵称
        readerComments.setMsgReplyAvatar(userInfo.getAvatarUrl());//头像
        readerComments.setMsgReplyId(msgReplyId);//回复者id
        readerComments.setMsgReplyContent(msgReplyContent);//回复者内容
        readerComments.setMsgReplyTime(new Timestamp(System.currentTimeMillis()));
        Alerts alert = alertService.findByAskIdR(askId, 1);
        if (alert == null) {
            Alerts alerts = new Alerts();
            alerts.setPostId(askId);//问题id
            alerts.setAlertType(1);//提问的为1，交流的为2
            alerts.setAskId(readerComments.getMsgSubmitId());//发问人的id
            alerts.setCreateTime(new Timestamp(System.currentTimeMillis()));
            alertService.insertSelective(alerts);
        }
        readerService.updateInfo(readerComments);
        responseVO.setEcode(0);
        responseVO.setEmsg("回复成功");
        return responseVO;
    }

    //获取单独问题数据
    @GetMapping("/getOneAsk")
    @ResponseBody
    public ResponseVO getOneAsk(int askId){
        ResponseVO responseVO = new ResponseVO();
        ReaderComments readerComments = readerService.findById(askId);
        if(readerComments == null){
            responseVO.setEcode(1);
            responseVO.setEmsg("没有找到相关数据");
        }else {
            responseVO.setData(readerComments);
        }
        return responseVO;
    }

}
