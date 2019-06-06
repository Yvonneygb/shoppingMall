package com.wyu.infolib.controller.wx;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.entity.Communication;
import com.wyu.infolib.entity.ReaderComments;
import com.wyu.infolib.entity.UserInfo;
import com.wyu.infolib.service.CommunicationService;
import com.wyu.infolib.service.ReaderService;
import com.wyu.infolib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/31 19:24
 * @Version 1.0
 */
@RestController
@RequestMapping("/wx/user")
public class WxUserController {

    @Autowired
    UserService userService;

    @Autowired
    CommunicationService communicationService;

    @Autowired
    ReaderService readerService;

    //拿到我的提问帖
    @GetMapping("/getMyAskPage")
     public ResponseVO getMyAskPage(PageVO pageVO, int userId){
        ResponseVO responseVO = new ResponseVO();
        try {
            List<ReaderComments> list = readerService.getMyAskPage(pageVO, userId);
            responseVO.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("获取失败，请重试");
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    //拿到我的帖子---交流贴
    @GetMapping("/getMyPostPage")
    public ResponseVO getMyPostPage(PageVO pageVO, int userId){
        ResponseVO responseVO = new ResponseVO();
        try {
            List<Communication> list = communicationService.getMyPostPage(pageVO, userId);
            responseVO.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("获取失败");
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    //用户注册，先用工号或学号去找，看是否被人注册了，然后再新建用户信息
    @PostMapping("/reg")
    public ResponseVO reg(@RequestBody Map<String, Object> reqMap) {
        ResponseVO responseVO = new ResponseVO();
        if (reqMap != null) {
            String idCard = reqMap.get("idCard").toString();//工号或者学号
            String realName = reqMap.get("realName").toString();//真实姓名
            String college = reqMap.get("college").toString();//专业
            String openid = reqMap.get("openid").toString();//openid
            String nickName = reqMap.get("nickName").toString();//昵称
            String avatar = reqMap.get("avatar").toString();//头像url
            int sex = Integer.valueOf(reqMap.get("sex").toString());
            UserInfo userInfo = userService.findByIdCard(idCard);
            if (userInfo != null) {
                responseVO.setEcode(0);
                responseVO.setEmsg("用户已经被注册，请联系图书馆管理员处理");
            } else {
                UserInfo user = new UserInfo();
                user.setUserType(1);//学生用户
                user.setAuthentication(2);//正在审核
                user.setAvatarUrl(avatar);
                user.setNickname(nickName);
                user.setSex(sex);
                user.setUserCollege(college);
                user.setUserIdCard(idCard);
                user.setUserName(realName);
                user.setOpenid(openid);
                user.setRegTime(new Timestamp(System.currentTimeMillis()));
                user.setLoginTime(new Timestamp(System.currentTimeMillis()));
                userService.save(user);
                responseVO.setEcode(0);
                responseVO.setEmsg("认证申请成功提交，请耐心等待审核");
            }
            return responseVO;
        }
        responseVO.setEcode(1);
        responseVO.setEmsg("系统出现异常，请重试");
        return responseVO;
    }

    //获取认证状态
    @GetMapping("/getAuthDeal")
    public ResponseVO getAuthDeal(@RequestParam("openid") String openid) {
        ResponseVO responseVO = new ResponseVO();
        //String openid = reqMap.get("openid").toString();//openid
        UserInfo userInfo = userService.findByOpenid(openid);
        responseVO.setEcode(0);
        if (userInfo == null) {
            responseVO.setEmsg("无此用户认证信息");
        } else {
            int flag = userInfo.getAuthentication();//1认证通过，2审核中，3审核不通过，-1用户禁用
            String msg = "";
            switch (flag) {
                case -1:
                    msg = "用户已被禁用，请联系图书馆管理员处理";
                    break;
                case 1:
                    msg = "用户认证成功";
                    break;
                case 2:
                    msg = "用户资料审核中";
                    break;
                case 3:
                    msg = "审核不通过,请联系图书馆管理员";
                    break;
            }
            responseVO.setEmsg(msg);
            responseVO.setData(flag);
        }
        return responseVO;
    }
}
