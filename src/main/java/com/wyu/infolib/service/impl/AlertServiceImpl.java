package com.wyu.infolib.service.impl;

import com.wyu.infolib.dao.AlertsMapper;
import com.wyu.infolib.entity.Alerts;
import com.wyu.infolib.service.AlertService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/1 23:25
 * @Version 1.0
 */
@Service
public class AlertServiceImpl implements AlertService {

    @Resource
    AlertsMapper alertsMapper;



    @Override
    public int insertSelective(Alerts alerts) {
        return alertsMapper.insertSelective(alerts);
    }

    @Override
    public List<Alerts> getList(int askUserId) {
        return alertsMapper.getList(askUserId);
    }

    @Override
    public Alerts findById(int alertId) {
        return alertsMapper.selectByPrimaryKey(alertId);
    }

    @Override
    public int update(Alerts alerts) {
        return alertsMapper.updateByPrimaryKeySelective(alerts);
    }

    @Override
    public Alerts findByAskIdR(int askId, int type) {
        List<Alerts> list = alertsMapper.findByAskIdR(askId, type);
        if (list.size() != 0){
            return list.get(0);
        }
        return null;
    }

    public static void main(String[] args){
        String appKey = "e3d9512f96b4f184c3b95696";
        String secret = "22925e6c812d52ec592e1e36";
        String auth = appKey + ":" + secret;
        auth = Base64.getEncoder().encodeToString(auth.getBytes());
        System.out.println(auth);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        auth = "Basic " + auth;
        //header.set("Authorization", auth);
        header.add("Authorization", auth);
        //HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>();

        ResponseEntity<String> response = restTemplate.getForEntity("https://api.jpush.cn/v3/push/validate",String.class);
        //ResponseEntity<String> response = restTemplate.getForEntity("https://www.baidu.com/",String.class);
        System.out.println(response);
    }
}
