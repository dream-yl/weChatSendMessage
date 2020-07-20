package com.example.demo.entity;

/**
 * 模板详细信息
 * 根据需求自己更改
 */
public class TemplateData {
    private String value;
    private String color;
    public TemplateData(String value,String color){
        this.value = value;
        this.color = color;
    }
    public TemplateData(){

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
