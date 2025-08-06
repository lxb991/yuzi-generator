package ${basePackage}.generator;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 调用动静态方法生成目标代码项目
 */
public class MainGenerator {
    public static void doGenerator(Object model) throws TemplateException, IOException {
        //生成静态文件

        String inRootPath = "${fileConfig.inputRootPath}";
        String outRootPath = "${fileConfig.outputRootPath}";

        String inputPath ;
        String outputPath ;

<#list fileConfig.files as fileInfo>
        inputPath = new File(inRootPath,"${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outRootPath,"${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == "static">
        StaticFileGenerator.copyFilesByHutool(inputPath,outputPath);
    <#else>
        DynamicFileGenerator.doGenerator(inputPath,outputPath,model);
    </#if>
</#list>

    }
}
