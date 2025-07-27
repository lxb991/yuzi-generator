package com.yupi.cli.pattern;

/**
 * 具体命令，并与设备绑定
 */
public class TurnOnCommand implements Command {
    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    public void execute() {
        device.turnOn();
    }
}
