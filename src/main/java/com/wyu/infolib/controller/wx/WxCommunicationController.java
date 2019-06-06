package com.wyu.infolib.controller.wx;

import com.wyu.infolib.common.entity.CommunicationReplyVO;
import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.entity.Communication;
import com.wyu.infolib.entity.Reply;
import com.wyu.infolib.service.CommunicationService;
import com.wyu.infolib.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 交流贴
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/17 10:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/wx/communication")
public class WxCommunicationController {

    @Autowired
    CommunicationService communicationService;

    @Autowired
    ReplyService replyService;

    @GetMapping("/search")
    public ResponseVO getKeyPage(PageVO pageVO, String keyWord){
        ResponseVO responseVO = new ResponseVO();
        try {
            List<Communication> list = communicationService.getKeyPage(pageVO,keyWord);
            responseVO.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("获取失败，请重试");
        }
        return responseVO;
    }

    @GetMapping("/getListPage")
    public ResponseVO getListPage(PageVO pageVO){
        ResponseVO responseVO = new ResponseVO();
        try {
            List<Communication> list = communicationService.getListPageWx(pageVO);
            responseVO.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("获取信息出错");
        }
        return responseVO;
    }

    @PostMapping("/ask")
    public ResponseVO ask(@RequestBody Map<String, Object> reqMap){
        ResponseVO responseVO = new ResponseVO();
        Communication vo = new Communication();
        vo.setIsShow(0);//进入审核状态
        vo.setMsgSubmitTime(new Date());
        vo.setAvatarUrl(reqMap.get("avatarUrl").toString());
        vo.setContent(reqMap.get("content").toString());
        vo.setSubmitUserId(Integer.valueOf(reqMap.get("submitUserId").toString()));
        vo.setNickname( reqMap.get("nickname").toString());
        try {
            communicationService.save(vo);
            responseVO.setData(vo.getId());
            responseVO.setEmsg("正在审核，请耐心等候");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("发帖失败，请重试");
        }
        return responseVO;
    }

    @PostMapping("/reply")
    public ResponseVO reply(@RequestBody Map<String, Object> reqMap){
        ResponseVO responseVO = new ResponseVO();
        Reply reply = new Reply();
        reply.setCommunicationId(Integer.valueOf(reqMap.get("communicationId").toString()));
        reply.setReplyContent(reqMap.get("replyContent").toString());
        if(reqMap.get("replyParentId") != "" && reqMap.get("replyParentName") != ""){
            reply.setReplyParentId(Integer.valueOf(reqMap.get("replyParentId").toString()));
            reply.setReplyParentName(reqMap.get("replyParentName").toString());
        }
        reply.setUserId(Integer.valueOf(reqMap.get("userId").toString()));
        reply.setUserName(reqMap.get("userName").toString());
        reply.setIsShow(0);
        reply.setReplyTime(new Date());
        responseVO.setEmsg("回复成功，审核通过后可见");
        try {
            replyService.save(reply);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("回复失败，请重试");
        }
        return responseVO;
    }

}
