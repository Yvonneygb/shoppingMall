package com.shopping.shoppingMall.controller.wx;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shopping.shoppingMall.common.entity.ResponseVO;
import com.shopping.shoppingMall.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @Description 微信登录相关控制类
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/31 16:59
 * @Version 1.0
 */
@Controller
@RequestMapping("/wx/user")
public class WxUserLoginController {

    @Autowired
    private UserService userService;

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * appid 小程序id 微信公众平台 设置中的开发设置中可以查到，相当于qq的qq号 建议单独创建一个配置文件储存此类值和函数
     */
    public static final String APPID = "wxeb18fd1869e183e6";
    /**
     * AppSecret 小程序密钥 微信公众平台 设置中的开发设置中可以查询 但是每次查询会重置密钥 相当于qq的qq密码 建议单独储存
     */
    public static final String SECRET = "dc8b744834f74d7bee9f75bd9709dd67";

    /**
     * 用于获取用户openid的接口网址 其中%s将会用String.format函数替换为实际的值 建议单独储存
     */
    public static final String Web_access_tokenhttps = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    /**
     * 通过APPID，SECET，code组合出用于获取用户openid的实际网址 建议单独储存
     */
    public static String getWebAccess(String code) {
        return String.format(Web_access_tokenhttps, APPID, SECRET, code);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseVO login(@RequestBody Map<String, Object> reqMap) {
        ResponseVO responseVO = new ResponseVO();
        String code = reqMap.get("code").toString();
        System.out.println("jc_code" + code);
        System.out.println(">>>微信小程序登录，请求数据为[ code:" + code);
        // 获取用户openid的实际网址
        String token = getWebAccess(code);
        // 通过HttpGet方法将token发送至微信服务器并获得其回执
        String rec = httpGet(token);
        System.out.println("微信回执为：/n" + rec);
        JSONObject json = JSON.parseObject(rec);
        String openid = "";
        // 获取回执的openid
        if (json != null) {
            // 获取openid
            openid = json.getString("openid");
            System.out.print("openid=" + openid + "......");
            UserInfo userInfo = userService.findByOpenid(openid);
            //System.out.println(userInfo.toString());
            if(userInfo == null) {
                //新用户
                responseVO.setEmsg("新用户");
                responseVO.setData(openid);
            }else {
                responseVO.setEmsg("获取用户信息成功");
                userInfo.setLoginTime(new Timestamp(System.currentTimeMillis()));
                userInfoMapper.updateByPrimaryKeySelective(userInfo);
                userInfo.setUserPassword("");
                userInfo.setRemarks("");
                responseVO.setData(userInfo);
            }
            return responseVO;
        }
        responseVO.setEcode(1);
        responseVO.setEmsg("获取openid失败");
        return responseVO;
    }

    /**
     * 通过HttpGet类发送GET请求并获取返回信息
     *
     * @param path
     *            发送至的网址
     * @return
     */
    public String httpGet(String path) {
        if (path == null) {
            return null;
        }
        String rec = null;
        HttpGet get = new HttpGet(path);
        try {
            HttpResponse response = HttpClients.createDefault().execute(get);
            HttpEntity entity = response.getEntity();
            rec = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rec;
    }

}
