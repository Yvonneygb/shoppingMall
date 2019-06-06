package com.wyu.infolib.controller.wx;

import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.common.utils.UploadImgUtil;
import com.wyu.infolib.entity.Communication;
import com.wyu.infolib.entity.ReaderComments;
import com.wyu.infolib.service.CommunicationService;
import com.wyu.infolib.service.ReaderService;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/8 10:47
 * @Version 1.0
 */
@RestController
@RequestMapping("/wx/img")
public class WxUploadController {

    @Resource
    ReaderService readerService;

    @Resource
    CommunicationService communicationService;

    //上传图片
    @RequestMapping("/upload")
    public ResponseVO upload(HttpServletRequest request, @RequestParam("imgFile") MultipartFile imgFile) throws IOException {
        ResponseVO responseVO = new ResponseVO();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd_ss");
        int askId = Integer.valueOf(request.getParameter("postId"));//贴id
        int askType = Integer.valueOf(request.getParameter("postType"));//发帖类型,1提问，2交流
        //int imgNum = Integer.parseInt(request.getParameter("imgIndex"));
        String fileName = "";//保存文件的名字
        String imgStr = "";//数据库读出的urls
        String saveImgStr = "";//保存的urls
        List<String> list = new ArrayList<>();
        ReaderComments readerComments = new ReaderComments();
        Communication communication = new Communication();
        System.out.println("我进来了");
        if (askType == 1) {
            readerComments = readerService.findById(askId);
            imgStr = readerComments.getMsgImgUrls();
            if (imgStr != null) {
                list = Arrays.asList(imgStr.split(","));
            }
        }else if (askType == 2){
            communication = communicationService.findById(askId);
            imgStr = communication.getImgUrls();
            if (imgStr != null) {
                list = Arrays.asList(imgStr.split(","));
            }
        }
        if (imgStr != null) {
            int num = list.size() + 1;
            fileName = formatter.format(date) + "_type_" +
                    askType + "_" + askId + "_" + num;
            saveImgStr = imgStr + ",";
        } else {
            fileName = formatter.format(date) + "_type_" +
                    askType + "_" + askId + "_1";
        }
        ResponseVO rvo = UploadImgUtil.uploadImg(imgFile, fileName);
        if (rvo.getEcode() == 0) {
            String type = (String) rvo.getData();
            String appendStr = "/image/" + fileName + "." + type;
            saveImgStr = saveImgStr + appendStr;
            if (askType == 1) {
                readerComments.setMsgImgUrls(saveImgStr);
                readerService.updateInfo(readerComments);
            }else if(askType == 2){
                communication.setImgUrls(saveImgStr);
                communicationService.updateCommunication(communication);
            }
            responseVO.setEmsg("保存成功");
        } else {
            return rvo;
        }
        return responseVO;
    }

    @Test
    public void main() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd_ss");
        System.out.println(formatter.format(date));
        String test = "4545,21312";
        List<String> list = Arrays.asList(test.split(","));
        System.out.println(list);
    }
}
