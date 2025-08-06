package com.yupi.maker.generator;

import java.io.*;

public class JarGenerator {
    public static void doGenerator(String projectDir) throws IOException, InterruptedException {
        String winMvnCommand = "mvn.cmd clean package -DskipTests=true";
        ProcessBuilder processBuilder = new ProcessBuilder(winMvnCommand.split(" "));
        processBuilder.directory(new File(projectDir));

        Process process = processBuilder.start();

        //读取命令的输出
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }

        //等待命令完成
        int exitValue = process.waitFor();
        System.out.println("命令执行结束，退出码："+exitValue);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerator("D:\\code\\yuzi-generator\\yuzi-generator-maker\\generator");
    }
}
