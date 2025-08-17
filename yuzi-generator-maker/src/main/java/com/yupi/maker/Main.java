package com.yupi.maker;

//import com.yupi.maker.cli.CommandExecutor;

import com.yupi.maker.generator.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator mainGenerator = new MainGenerator();
        //当执行doGenerate时，调用buildDest时，会执行子类的buildDest！
        mainGenerator.doGenerate();
    }
}
