package com.yupi.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * 动态文件生成
 */
public class DynamicFileGenerator {
    public static void doGenerator(String inputPath,String outputPath,Object model) throws IOException, TemplateException {

        //设置版本
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        //设置模板文件的目录
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);
        //设置编码
        configuration.setDefaultEncoding("utf-8");
        //添加模板并获取
        String tempalteName = new File(inputPath).getName();
        Template template = configuration.getTemplate(tempalteName);
        //设置输出文件名
//        FileWriter out = new FileWriter(outputPath);

        //如果文件不存在则创建
        if(!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }
        Writer out = new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-8");//解决动态生成文件内容乱码问题
        //开始生成
        template.process(model,out);
        //关流
        out.close();
    }
}
