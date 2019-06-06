package com.wyu.infolib.controller.wx;

import com.wyu.infolib.common.entity.AlertsShowVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.entity.Alerts;
import com.wyu.infolib.entity.ReaderComments;
import com.wyu.infolib.entity.Reply;
import com.wyu.infolib.service.AlertService;
import com.wyu.infolib.service.ReaderService;
import com.wyu.infolib.service.ReplyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/31 20:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/wx/alert")
public class WxAlertController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private ReplyService replyService;

    @GetMapping("/getList")
    //拿到未读的信息list
    public ResponseVO getList(@RequestParam("askUserId") int askUserId){
        ResponseVO responseVO = new ResponseVO();
        //int askUserId = Integer.valueOf(reqMap.get("askUserId").toString());
        responseVO.setEmsg("获取成功");
        responseVO.setEcode(0);
        List<Alerts> list = alertService.getList(askUserId);
        List<AlertsShowVO> alertsShowVOS = new ArrayList<AlertsShowVO>();
        int alertLen = list.size();
        for(int i=0; i < alertLen; i++){
            AlertsShowVO alVO = new AlertsShowVO();
            int type = list.get(i).getAlertType();//获取消息类型
            int postId = list.get(i).getPostId();//获取帖子id
            alVO.setPostType(type);
            alVO.setAlertId(list.get(i).getId());
            if(type == 1){
                ReaderComments rc = readerService.findById(postId);
                alVO.setPostId(postId);
                alVO.setReplyName(rc.getMsgReply());
                alVO.setReplyAvatarUrl(rc.getMsgReplyAvatar());
                alVO.setReplyContent(rc.getMsgReplyContent());
                alVO.setReplyTime(rc.getMsgReplyTime());
            }else if(type == 2){
                Reply rc = replyService.findById(postId);
                alVO.setPostId(rc.getCommunicationId());
                alVO.setReplyName(rc.getUserName());
                alVO.setReplyContent(rc.getReplyContent());
                alVO.setReplyTime(rc.getReplyTime());
            }
            System.out.println(alVO.toString());
            alertsShowVOS.add(alVO);
        }
        responseVO.setData(alertsShowVOS);
        return responseVO;
    }

    //标志已读
    @PostMapping("/isRead")
    public ResponseVO isRead(@RequestBody Map<String, Object> reqMap){
        ResponseVO responseVO = new ResponseVO();
        int alertId = Integer.valueOf(reqMap.get("alertId").toString());
        Alerts alerts = alertService.findById(alertId);
        alerts.setIsRead(1);
        alerts.setReadTime(new Timestamp(System.currentTimeMillis()));
        int flag = alertService.update(alerts);
        if (flag == 1){
            responseVO.setEcode(0);
            responseVO.setEmsg("处理成功");
        }else {
            responseVO.setEcode(1);
            responseVO.setEmsg("处理失败");
        }
        return responseVO;
    }

}
