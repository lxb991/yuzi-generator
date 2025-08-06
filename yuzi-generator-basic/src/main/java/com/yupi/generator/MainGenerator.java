package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 调用动静态方法生成目标代码项目
 */
public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        //准备数据
        MainTemplateConfig dataModel = new MainTemplateConfig();
        dataModel.setLoop(true);
        dataModel.setAuthor("方世玉");
        dataModel.setOutputText("总和是：123");
        doGenerator(dataModel);
    }
    public static void doGenerator(Object model) throws TemplateException, IOException {
        //生成静态文件
//        String projPath = System.getProperty("user.dir");
//        File parentFile = new File(projPath).getParentFile();
//        String inputPath = new File(parentFile,"yuzi-generator-demo-projects/acm-template").getAbsolutePath();
//        String outputPath = projPath;

        String inRootPath = "D:\\code\\yuzi-generator\\yuzi-generator-demo-projects\\acm-template-pro";
        String outRootPath = "D:\\code\\yuzi-generator\\acm-template-pro";

        String inputPath ;
        String outputPath ;

        inputPath = new File(inRootPath,"README.md").getAbsolutePath();
        outputPath = new File(outRootPath,"README.md").getAbsolutePath();

        StaticGenerator.copyFilesByHutool(inputPath,outputPath);

//        inputPath = new File(inRootPath,".gitignore").getAbsolutePath();
//        outputPath = new File(outRootPath,".gitignore").getAbsolutePath();

//        StaticGenerator.copyFilesByHutool(inputPath,outputPath);

        //生成动态文件
//        String inputDyPath = projPath + File.separator + "src/main/resources/template/MainTemplate.java.ftl";
//        String outputDyPath = projPath+ File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";

        inputPath = new File(inRootPath,"src/com/yupi/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outRootPath,"src/com/yupi/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,model);

//        DynamicGenerator.doGenerator(inputDyPath,outputDyPath,model);

    }
}
