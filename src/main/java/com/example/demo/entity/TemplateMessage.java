package com.example.demo.entity;

import java.util.Map;
/**
 * 注册成功，通知模板消息实体类
 */

public class TemplateMessage {
    private String touser; //用户OpenID
    private String template_id; //模板消息ID
    private String url; //URL置空，在发送后，点模板消息进入一个空白页面（ios），或无法点击（android）。
    private String topcolor; //标题颜色
    private Map<String, TemplateData> templateData; //模板详细信息

    public static TemplateMessage New() {
        return new TemplateMessage();
    }
    public TemplateMessage(){


    }
    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public Map<String, TemplateData> getTemplateData() {
        return templateData;
    }

    public void setTemplateData(Map<String, TemplateData> templateData) {
        this.templateData = templateData;
    }
}
