package com.wyu.infolib.controller.platform;

import com.wyu.infolib.common.entity.ReaderAskPageVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.entity.ReaderComments;
import com.wyu.infolib.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description 读者提问控制类
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/3 10:20
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/reader")
public class ReaderController {

    @Autowired
    ReaderService readerService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseVO readerList(ReaderAskPageVO readerAskPageVO){
        ResponseVO responseVO = new ResponseVO();
        try {
            List<ReaderComments> list = readerService.getListPage(readerAskPageVO);
            responseVO.setData(list);
            responseVO.setEmsg("获取成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("获取失败");
        }
        return responseVO;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseVO addReader(ReaderComments readerComments){
        ResponseVO responseVO = new ResponseVO();
        if (readerComments.getMsgSubmitTime() == null){
            readerComments.setMsgSubmitTime(new Timestamp(System.currentTimeMillis()));
        }
        try {
            readerService.save(readerComments);
            responseVO.setEmsg("新增成功");
        } catch (Exception e) {
            responseVO.setEcode(1);
            e.printStackTrace();
            responseVO.setEmsg("新增失败");
        }
        return responseVO;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVO updateReader(ReaderComments readerComments){
        ResponseVO responseVO = new ResponseVO();
        try {
            if(readerComments.getMsgSubmitTime() == null){
                readerComments.setMsgSubmitTime(new Timestamp(System.currentTimeMillis()));
            }
            readerService.updateInfo(readerComments);
            responseVO.setEmsg("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("更新失败");
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    @PostMapping("/del")
    @ResponseBody
    public ResponseVO deleteReader(String ids){
        ResponseVO responseVO = new ResponseVO();
        try {
            readerService.deleteReader(ids);
            responseVO.setEmsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("删除失败");
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    @PostMapping("/changeShow")
    @ResponseBody
    public ResponseVO changeShow(int id){
        ResponseVO responseVO = new ResponseVO();
        ReaderComments readerComments = readerService.findById(id);
        int isShow = readerComments.getMsgShow() == 1 ? 0 : 1;
        readerComments.setMsgShow(isShow);
        try {
            readerService.updateInfo(readerComments);
            responseVO.setEmsg("显示状态改变成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("显示状态改变失败");
            responseVO.setEcode(1);
        }
        return responseVO;
    }
}
