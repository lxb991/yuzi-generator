package com.yupi.maker.meta.enums;


public enum ModelTypeEnum {
    STRING("字符串", "string"),
    BOOLEAN("布尔", "boolean");

    private final String text;
    private final String value;

    ModelTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
