package com.yupi.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

/**
 * Callable：交互式 命令行
 */
public class Login implements Callable<Integer> {
    @Option(names = {"-u", "--user"},arity = "0..1",description = "User name", interactive = true)
    String user;

    //interactive = true 支持交互式
    @Option(names = {"-p", "--password"},arity = "0..1",description = "Passphrase", interactive = true)
    String password;

    @Option(names = {"-cp","checkPassword"},arity = "0..1",description = "checkPassword",interactive = true)
    String checkpassword;

    public Integer call() throws Exception {
        System.out.println("user = " + user);
        System.out.println("password = " + password);
        System.out.println("checkpassword = " + checkpassword);
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(args.toString());
        new CommandLine(new Login()).execute("-u", "user123","-p");
    }
}
