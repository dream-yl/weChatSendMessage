package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.AccessToken;
import com.example.demo.entity.TemplateData;
import com.example.demo.utils.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.generics.tree.VoidDescriptor;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/weChat")
public class TestWX {
    @Autowired
    private RedisConfig RedisConfig;
    /*public static void main(String[] args) {
        //新增用户成功 - 推送微信消息
        senMsg("oTQdNwz-gUeDfLgAQSiRhLZzj1O4");
    }*/
    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(){
        senMsg("oTQdNwz-gUeDfLgAQSiRhLZzj1O4");
        return "success";
    }

    /**
     * 测试redis
     * @return
     */
    @RequestMapping("/redis")
    @ResponseBody
    public String ceshi(){
        AccessToken accessToken =RedisConfig.getAccessToken();
        if (accessToken == null){
            RedisConfig.setAccessToken(WX_TokenUtil.getWXToken().getAccessToken(),10);
            return "新增缓存"+RedisConfig.getAccessToken().getAccessToken();
        }
        return "老数据"+accessToken.getAccessToken();
    }

    static void senMsg(String openId){
        //用户是否订阅该公众号标识 (0代表此用户没有关注该公众号 1表示关注了该公众号)
        Integer  state= WX_UserUtil.subscribeState(openId);
        System.out.println("state:"+state);
        // 绑定了微信并且关注了服务号的用户 , 注册成功-推送注册短信
        if(state==1){
            Map<String, TemplateData> param = new HashMap<>();
            param.put("User",new TemplateData("杨先生","#173177"));
            param.put("Date",new TemplateData("07月18日 19时24分","#173177"));
            param.put("CardNumber",new TemplateData("0426","#173177"));
            param.put("Type",new TemplateData("兰州拉面","#173177"));
            param.put("Money",new TemplateData("人民币5,000,000.00元","#173177"));
            param.put("DeadTime",new TemplateData("06月08日19时24分","#173177"));
            param.put("Left",new TemplateData("31872134.09","#173177"));

            JSON.toJSONString(param);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(param));
            //调用发送微信消息给用户的接口
            WX_TemplateMsgUtil.sendWechatMsgToUser(openId,"lsCnZkSUOIeB4q8ZuHRli3onuciVZPS0GvCh2i06TB4", "http://www.baidu.com",
                    "#FFFFFF", jsonObject);


            //获取公众号的自动回复规则
            String urlinfo="https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token="+ WX_TokenUtil.getWXToken().getAccessToken();
            JSONObject joinfo = WX_HttpsUtil.httpsRequest(urlinfo, "GET", null);
            Object o=joinfo.get("is_add_friend_reply_open");
            // System.out.println("o:"+joinfo);
            String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx395cb56f846049fc&secret=b8285bb7fb16553fd11feafb52c332f7";
            JSONObject Token = WX_HttpsUtil.httpsRequest(getTokenUrl, "GET", null);
            //System.out.println("Token:"+Token);


        }
    }

}
