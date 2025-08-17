package com.yupi.maker.generator;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator extends GenerateTemplate {
    @Override
    protected void buildDest(String outputPath, String jarPath, String shellOutputFilePath, String sourceCopyDestPath) {
        System.out.println("不要输出dest...");
    }
}
