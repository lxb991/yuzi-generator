package com.yupi.cli.pattern;

/**
 * 接受者：命令要操作的对象，知道如何根据命令执行操作
 * 电视机
 */
public class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + " 设备打开");
    }

    public void turnOff() {
        System.out.println(name + " 设备关闭");
    }
}
