package com.wyu.infolib.controller.platform;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.entity.Communication;
import com.wyu.infolib.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description  交流贴
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/3 10:21
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/jlt")
public class CommunicationController {

    @Autowired
    CommunicationService communicationService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseVO communicationList(PageVO pageVO){
        ResponseVO responseVO = new ResponseVO();
        try {
            List<Communication> list = communicationService.communicationList(pageVO);
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
    public ResponseVO addCommunication(Communication communication){
        ResponseVO responseVO = new ResponseVO();
        if(communication.getMsgSubmitTime() == null){
            communication.setMsgSubmitTime(new Timestamp(System.currentTimeMillis()));
        }
        try {
            communicationService.save(communication);
            responseVO.setEmsg("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("新增失败");
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVO updateCommunication(Communication communication){
        ResponseVO responseVO = new ResponseVO();
        try {
            communicationService.updateCommunication(communication);
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
    public ResponseVO deleteCommunication(String ids){
        ResponseVO responseVO = new ResponseVO();
        try {
            communicationService.deleteCommunication(ids);
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
        Communication communication = communicationService.findById(id);
        int isShow = communication.getIsShow() == 1 ? 0 : 1;//取相反值
        communication.setIsShow(isShow);
        try {
            communicationService.updateCommunication(communication);
            responseVO.setEmsg("改变状态成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("改变状态失败");
            responseVO.setEcode(1);
        }
        return responseVO;
    }
}
