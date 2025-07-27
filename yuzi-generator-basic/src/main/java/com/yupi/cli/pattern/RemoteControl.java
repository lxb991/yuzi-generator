package com.yupi.cli.pattern;

/**
 * 调用者：接受客户端的命令并执行
 * 遥控器
 */
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
