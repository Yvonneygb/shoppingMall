package com.wyu.infolib.controller.wx;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.common.entity.ReaderAskPageVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.common.utils.AlertPushUtil;
import com.wyu.infolib.entity.ReaderComments;
import com.wyu.infolib.service.ReaderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/31 22:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/wx/reader")
public class WxReaderController {

    @Autowired
    ReaderService readerService;

    //搜索词返回数据
    @GetMapping("/search")
    public ResponseVO getKeyPage(PageVO pageVO, String keyWord){
        ResponseVO responseVO = new ResponseVO();
        try {
            List<ReaderComments> list = readerService.getKeyPage(pageVO, keyWord);
            responseVO.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("获取失败");
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    //wx用户发起提问
    @PostMapping("/postAsk")
    public ResponseVO postAsk(@RequestBody Map<String, Object> reqMap){
        ResponseVO responseVO = new ResponseVO();
        System.out.println("进来了");
        if (reqMap == null){
            responseVO.setEmsg("数据出错");
            responseVO.setEcode(1);
        }else {
            String content = reqMap.get("content").toString();//内容
            int userId= Integer.valueOf(reqMap.get("userId").toString());//提问人的id
            String nickName = reqMap.get("nickName").toString();//用户昵称
            String avatar = reqMap.get("avatar").toString();//用户头像
            ReaderComments readerComments = new ReaderComments();
            readerComments.setMsgSubmit(nickName);
            readerComments.setMsgSubmitAvatar(avatar);
            readerComments.setMsgSubmitId(userId);
            readerComments.setMsgSubmitContent(content);
            readerComments.setMsgSubmitTime(new Timestamp(System.currentTimeMillis()));
            readerService.save(readerComments);
            responseVO.setData(readerComments.getId());
            responseVO.setEmsg("提问成功,请耐心等待审核");
            if(content.length()>=10) {
                try {
                    AlertPushUtil.pushAlert(content.substring(0,10)+"...","test",readerComments.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    AlertPushUtil.pushAlert(content,"test",readerComments.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return responseVO;
    }

    //获取读者提问分页list
    @GetMapping("/list")
    public ResponseVO askList(ReaderAskPageVO pageVO){
        ResponseVO responseVO = new ResponseVO();
        pageVO.setIsShow(1);
        try {
            List<ReaderComments> list = readerService.getListPage(pageVO);
            responseVO.setData(list);
        } catch (Exception e) {
            responseVO.setEcode(1);
            e.printStackTrace();
        }
        return responseVO;
    }

    //根据id找到读者提问帖
    @PostMapping("/findById")
    public ResponseVO findById(@RequestBody Map<String, Object> reqMap){
        ResponseVO responseVO = new ResponseVO();
        int askId = Integer.valueOf(reqMap.get("askId").toString());
        try {
            ReaderComments readerComments = readerService.findById(askId);
            responseVO.setData(readerComments);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
        }
        return responseVO;
    }

}
