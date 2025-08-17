package com.yupi.maker.meta.enums;

public enum FileGenerateTypeEnum {
    STATIC("静态", "static"),
    DYNAMIC("文件", "dynamic");

    private final String text;
    private final String value;

    FileGenerateTypeEnum(String text, String value) {
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
