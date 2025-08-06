package com.yupi.maker.generator.file;

import com.yupi.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 调用动静态方法生成目标代码项目
 */
public class FileGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        //准备数据
        DataModel dataModel = new DataModel();
        dataModel.setLoop(true);
        dataModel.setAuthor("方世玉");
        dataModel.setOutputText("总和是：");
        doGenerator(dataModel);
    }
    public static void doGenerator(Object model) throws TemplateException, IOException {
        //生成静态文件
        String projPath = System.getProperty("user.dir");
        File parentFile = new File(projPath).getParentFile();
        String inputPath = new File(parentFile,"yuzi-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projPath;
        StaticFileGenerator.copyFilesByHutool(inputPath,outputPath);
        //生成动态文件
        String inputDyPath = projPath + File.separator + "src/main/resources/template/MainTemplate.java.ftl";
        String outputDyPath = projPath+ File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";
        DynamicFileGenerator.doGenerator(inputDyPath,outputDyPath,model);

    }
}
