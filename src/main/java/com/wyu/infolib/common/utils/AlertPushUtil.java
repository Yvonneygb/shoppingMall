package com.wyu.infolib.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wyu.infolib.common.entity.ResponseVO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @Description  消息提醒推送
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/24 14:55
 * @Version 1.0
 */
public class AlertPushUtil {

    private static final Logger log = LoggerFactory.getLogger(AlertPushUtil.class);
    private static final String masterSecret = "22925e6c812d52ec592e1e36";
    private static final String appKey = "e3d9512f96b4f184c3b95696";
    private static final String pushUrl = "https://api.jpush.cn/v3/push";
    //true生产环境
    private static final boolean apns_production = true;
    //离线消息保留时间 默认一天86400
    private static final int time_to_live = 86400 ;
    private static final String ALERT = "推送信息";
    /**
     * 极光推送
     */
    public static ResponseVO pushAlert(String alert, String jumpUrl ,int askId) {
        ResponseVO responseVO = new ResponseVO();
        try {
            String result = push(alert, jumpUrl, askId);
            JSONObject resData = JSONObject.parseObject(result);
            if (resData.containsKey("error")) {
                log.error("错误信息" + resData.getJSONObject("error").get("message"));
                responseVO.setEmsg(resData.getJSONObject("error").get("message").toString());
                responseVO.setEcode(1);
            } else {
                log.info("推送成功！");
                responseVO.setEmsg("推送成功");
            }
        } catch (Exception e) {
            log.error("信息推送失败！", e);
            responseVO.setEmsg(e.toString());
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    /**
     * 组装极光推送专用json串
     * @param alert
     * @return json
     */
    public static JSONObject generateJson(String alert, String jumpUrl, int askId){
        JSONObject json = new JSONObject();

        JSONArray platform = new JSONArray();//平台
        platform.add("android");
        platform.add("ios");

        JSONObject notification = new JSONObject();//通知内容
        JSONObject android = new JSONObject();//android通知内容
        android.put("alert", alert);
        android.put("builder_id", 1);
        JSONObject intent = new JSONObject();//指定跳转页面
        intent.put("url", jumpUrl);
        android.put("intent", intent);

        notification.put("android", android);

        JSONObject options = new JSONObject();//设置参数
        options.put("time_to_live", Integer.valueOf(time_to_live));
        options.put("apns_production", apns_production);

        JSONObject message = new JSONObject();//自定义消息
        message.put("msg_content","数据相关参数");
        JSONObject extras = new JSONObject();//传的参数
        extras.put("askId", askId);//提问的id
        message.put("extras", extras);

        json.put("platform", "all");//所有平台
        json.put("audience", "all");//所有人
        json.put("notification", notification);
        json.put("options", options);
        json.put("message", message);
        return json;

    }

    /**
     * 推送方法-调用极光API
     */
    public static String push(String alert, String jumpUrl, int askId){
        String base64_auth_string = encryptBASE64(appKey + ":" + masterSecret);
        String authorization = "Basic " + base64_auth_string;
        return sendPostRequest(generateJson(alert, jumpUrl, askId).toString(),authorization);
    }

    /**
     * 发送Post请求（json格式）
     */
    public static String sendPostRequest(String data,String authorization){
        HttpPost httpPost = new HttpPost(pushUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        StringEntity entity = new StringEntity(data, "UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Authorization",authorization.trim());
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     　　　　* BASE64加密工具
     　　　　*/
    public static String encryptBASE64(String str) {
        byte[] key = str.getBytes();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String str1 = base64Encoder.encodeBuffer(key);
        return str1;
    }

}
