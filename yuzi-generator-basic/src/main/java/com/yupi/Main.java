package com.yupi;

import com.yupi.cli.CommandExecutor;

public class Main {
    public static void main(String[] args) {
//        args = new String[]{"GenerateCommand", "-l", "-a", "-o"};
//        args = new String[]{"ConfigCommand" };
//        args = new String[]{"ListCommand" };
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecutor(args);
    }
}
