package com.yupi.model;

import lombok.Data;

/**
 * 动态模板配置
 */
@Data
public class MainTemplateConfig {

    private boolean loop;
    //如果不给字段赋值，则ftl报错
    private String author = "方世玉2";
    private String outputText = "总和是=";
}
