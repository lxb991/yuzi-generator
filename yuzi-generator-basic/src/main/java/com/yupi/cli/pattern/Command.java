package com.yupi.cli.pattern;

/**
 * 命令是接口或抽象类 定义执行操作的方法即【规则】 该方法封装具体的操作
 * 接口：解耦器
 */
public interface Command {
    void execute();
}
