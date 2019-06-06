package com.wyu.infolib.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
 * @Description 极光推送
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/24 14:22
 * @Version 1.0
 */
public class JiGuangPushUtil {

    private static final Logger log = LoggerFactory.getLogger(JiGuangPushUtil.class);
    private static final String masterSecret = "22925e6c812d52ec592e1e36" + 1;
    private static final String appKey = "e3d9512f96b4f184c3b95696";
    private static final String pushUrl = "https://api.jpush.cn/v3/push";
    private static final boolean apns_production = true;
    private static final int time_to_live = 60*60*24;
    private static final String ALERT = "推送信息";
    /**
     * 极光推送
     */
    public static void main(String[] args){
        String alias = "123456";//声明别名
        try{
            String result = push(pushUrl,alias,ALERT,appKey,masterSecret,apns_production,time_to_live);
            JSONObject resData = JSONObject.parseObject(result);
            System.out.println(result+"错误信息");
            if(resData.containsKey("error")){
                log.info("针对别名为" + alias + "的信息推送失败！");
                log.error("错误信息"+resData.getJSONObject("error").get("message"));
                //JSONObject error = JSONObject.(resData.get("error"));
                //log.info("错误信息为：" + error.get("message").toString());
            }else {
                log.info("针对别名为" + alias + "的信息推送成功！");
            }
        }catch(Exception e){
            log.error("针对别名为" + alias + "的信息推送失败！",e);
        }
    }

    /**
     * 组装极光推送专用json串
     * @param alias
     * @param alert
     * @return json
     */
    public static JSONObject generateJson(String alias,String alert,boolean apns_production,int time_to_live){
        JSONObject json = new JSONObject();
        JSONArray platform = new JSONArray();//平台
        platform.add("android");
        platform.add("ios");

        JSONObject audience = new JSONObject();//推送目标
        JSONArray alias1 = new JSONArray();
        alias1.add(alias);
        //audience.put("alias", alias1);

        JSONObject notification = new JSONObject();//通知内容
        JSONObject android = new JSONObject();//android通知内容
        android.put("alert", alert);
        android.put("builder_id", 1);
        JSONObject android_extras = new JSONObject();//android额外参数
        android_extras.put("type", "infomation");
        android.put("extras", android_extras);

        JSONObject ios = new JSONObject();//ios通知内容
        ios.put("alert", alert);
        ios.put("sound", "default");
        ios.put("badge", "+1");
        JSONObject ios_extras = new JSONObject();//ios额外参数
        ios_extras.put("type", "infomation");
        ios.put("extras", ios_extras);
        notification.put("android", android);
        notification.put("ios", ios);

        JSONObject options = new JSONObject();//设置参数
        options.put("time_to_live", Integer.valueOf(time_to_live));
        options.put("apns_production", apns_production);

        json.put("platform", platform);
        json.put("audience", "all");
        json.put("notification", notification);
        json.put("options", options);
        return json;

    }

    /**
     * 推送方法-调用极光API
     * @param reqUrl
     * @param alias
     * @param alert
     * @return result
     */
    public static String push(String reqUrl,String alias,String alert,String appKey,String masterSecret,boolean apns_production,int time_to_live){
        String base64_auth_string = encryptBASE64(appKey + ":" + masterSecret);
        String authorization = "Basic " + base64_auth_string;
        return sendPostRequest(reqUrl,generateJson(alias,alert,apns_production,time_to_live).toString(),"UTF-8",authorization);
    }

    /**
     * 发送Post请求（json格式）
     * @param reqURL
     * @param data
     * @param encodeCharset
     * @param authorization
     * @return result
     */
    public static String sendPostRequest(String reqURL, String data, String encodeCharset,String authorization){
        HttpPost httpPost = new HttpPost(reqURL);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
            StringEntity entity = new StringEntity(data, encodeCharset);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            httpPost.setHeader("Authorization",authorization.trim());
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            result = EntityUtils.toString(response.getEntity(), encodeCharset);
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
