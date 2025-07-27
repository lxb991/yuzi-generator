package com.yupi.cli.pattern;

/**
 * 命令下具体的操作
 * 遥控器上的某个功能按钮
 */
public class TurnOffCommand implements Command {
    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    public void execute() {
        device.turnOff();
    }
}
