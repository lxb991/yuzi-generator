package com.yupi.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * hutool工具复制文件
 */
public class StaticGenerator {
    public static void main(String[] args) {
        String projPath = System.getProperty("user.dir"); //D:\code\yuzi-generator\yuzi-generator-basic
        File parentFile = new File(projPath).getParentFile(); //D:\code\yuzi-generator
        //srcPath = D:\code\yuzi-generator\yuzi-generator-demo-projects\acm-template
        String srcPath = parentFile + File.separator +"yuzi-generator-demo-projects" +File.separator + "acm-template";
        String destPath = projPath;
        copyFilesByHutool(srcPath,destPath);
    }

    /**
     * 复制文件或目录 如果目标文件为目录，则将源文件以相同文件名拷贝到目标目录
     * srcPath – 源文件或目录
     * destPath – 目标文件或目录，目标不存在会自动创建（目录、文件都创建）
     * isOverride – 是否覆盖目标文件
     */
    public static void copyFilesByHutool(String srcPath,String destPath ){
        FileUtil.copy(srcPath,destPath,false);
    }
}
