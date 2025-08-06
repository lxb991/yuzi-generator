package com.yupi.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.yupi.maker.generator.file.DynamicFileGenerator;
import com.yupi.maker.meta.Meta;
import com.yupi.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        //1.获取模板数据
        Meta meta = MetaManager.getMetaObject();
//        System.out.println(meta.getModelConfig().getModels());
//        System.out.println(meta);
        //2.输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generator" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)){
            FileUtil.mkdir(outputPath);
        }

        //优化：从原始模板 复制到 生成的代码包中
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath,sourceCopyDestPath,false);

        //3.构造java包基础路径 项目名/src/main/java/com/yupi
        String basePackage = meta.getBasePackage();
        String outputBasePackagePath  = outputPath + File.separator + "/src/main/java/" + basePackage;

        //4.读取 resources目录 target/classes
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        //5.读取模板文件
        String inputFilePath  = inputResourcePath + File.separator + "template/java/model/DataModel.java.ftl";
        //6.制作输出文件
        String outputFilePath  = outputBasePackagePath + File.separator + "model/DataModel.java";
        //7.生成数据模型动态文件
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath,meta);

        //生成GenerateCommand动态文件
        inputFilePath  = inputResourcePath + File.separator + "template/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath  = outputBasePackagePath + File.separator + "cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath,meta);
        //生成ConfigCommand动态文件
        inputFilePath  = inputResourcePath + File.separator + "template/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath  = outputBasePackagePath + File.separator + "cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath,meta);
        //生成GenerateCommand动态文件
        inputFilePath  = inputResourcePath + File.separator + "template/java/cli/command/ListCommand.java.ftl";
        outputFilePath  = outputBasePackagePath + File.separator + "cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath,meta);
        //生成CommandExecutor动态文件
        inputFilePath  = inputResourcePath + File.separator + "template/java/cli/CommandExecutor.java.ftl";
        outputFilePath  = outputBasePackagePath + File.separator + "cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath,meta);
        //生成Main动态文件
        inputFilePath  = inputResourcePath + File.separator + "template/java/Main.java.ftl";
        outputFilePath  = outputBasePackagePath + File.separator + "Main.java";
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath,meta);

        //生成DynamicFileGenerator动态文件
        inputFilePath  = inputResourcePath + File.separator + "template/java/generator/DynamicFileGenerator.java.ftl";
        outputFilePath  = outputBasePackagePath + File.separator + "generator/DynamicFileGenerator.java";
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath,meta);

        //生成StaticFileGenerator动态文件
        inputFilePath  = inputResourcePath + File.separator + "template/java/generator/StaticFileGenerator.java.ftl";
        outputFilePath  = outputBasePackagePath + File.separator + "generator/StaticFileGenerator.java";
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath,meta);

        //生成MainGenerator动态文件
        inputFilePath  = inputResourcePath + File.separator + "template/java/generator/MainGenerator.java.ftl";
        outputFilePath  = outputBasePackagePath + File.separator + "generator/MainGenerator.java";
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath,meta);

        //生成Pom.xml动态文件
        inputFilePath  = inputResourcePath + File.separator + "template/pom.xml.ftl";
        outputFilePath  = outputPath + File.separator + "/pom.xml";
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath,meta);

        //生成README.md动态文件
        inputFilePath  = inputResourcePath + File.separator + "template/README.md.ftl";
        outputFilePath  = outputPath + File.separator + "/README.md";
        DynamicFileGenerator.doGenerator(inputFilePath,outputFilePath,meta);

        //构建jar包（打包）
        JarGenerator.doGenerator(outputPath);

        //封装脚本
        String shellOutputFilePath = outputPath + File.separator + "generator";
        String jarname = String.format("%s-%s-jar-with-dependencies.jar",meta.getName(),meta.getVersion());
        String jarPath = "target/" + jarname;
        ScriptGenerator.doGenerate(shellOutputFilePath,jarPath);

        //生成精简版项目
        //创建生成文件所在的目录
        String distOutputPath = outputPath + "-dest";
        //拷贝jar包
        String inputJarPath = outputPath + File.separator + jarPath;
        String outputJarPath = distOutputPath + File.separator + "target";
        if (!FileUtil.exist(outputJarPath)){
            FileUtil.mkdir(outputJarPath);
        }
        FileUtil.copy(inputJarPath,outputJarPath,false);

        //拷贝脚本文件;
        FileUtil.copy(shellOutputFilePath,distOutputPath,true);
        FileUtil.copy(shellOutputFilePath + ".bat",distOutputPath,true);
        //拷贝.source
        FileUtil.copy(sourceCopyDestPath,distOutputPath,true);


    }
}
