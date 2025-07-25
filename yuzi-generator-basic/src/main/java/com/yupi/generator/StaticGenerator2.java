package com.yupi.generator;

import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 递归方式复制文件
 */
public class StaticGenerator2 {
    public static void main(String[] args) throws IOException {
        String projPath = System.getProperty("user.dir"); //D:\code\yuzi-generator\yuzi-generator-basic
        File parentFile = new File(projPath).getParentFile(); //D:\code\yuzi-generator
        //srcPath = D:\code\yuzi-generator\yuzi-generator-demo-projects\acm-template
        String srcPath = parentFile + File.separator + "yuzi-generator-demo-projects" + File.separator + "acm-template";
        String destPath = projPath;
        copyFilesByRecursive(srcPath, destPath);
    }

    /**
     * 递归拷贝文件
     *
     * @param srcPath
     * @param destPath
     */
    public static void copyFilesByRecursive(String srcPath, String destPath) throws IOException {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        copyFileByRecursive(srcFile, destFile);
    }

    /**
     * 核心思路：先创建目录，遍历目录内的文件，再依次复制
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFileByRecursive(File srcFile, File destFile) throws IOException {
        //区分文件还是目录
        if (srcFile.isDirectory()) {
            System.out.println(srcFile.getName());
            File destOutFile = new File(destFile, srcFile.getName());// 将 srcFile 的目录名拼接到 destFile 的路径后
            //如果是目录，先创建目标目录
            if (!destOutFile.exists()) {
                destOutFile.mkdirs();
            }
            //获取目录下的所有文件和子目录
            File[] files = srcFile.listFiles();
            if (ArrayUtil.isEmpty(files)) {
                return; //空则直接返回
            }
            for (File file : files) {
                //递归拷贝下一级文件
                copyFileByRecursive(file, destOutFile);
            }
        } else {
            //是文件，直接拷贝到目标目录下
            /**
             * resolve:将 srcFile 的文件名（.txt）拼接到 destFile 的路径后
             * 例如：
             * destFile = D:\backup
             * srcFile.getName() = test.txt
             * 结果：D:\backup\test.txt。
             */
            Path destPath = destFile.toPath().resolve(srcFile.getName());
            Files.copy(srcFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING); //REPLACE_EXISTING:覆盖已存在的同名文件
        }
    }
}
