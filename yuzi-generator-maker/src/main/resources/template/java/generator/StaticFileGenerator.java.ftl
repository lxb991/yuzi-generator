package ${basePackage}.generator;

import cn.hutool.core.io.FileUtil;

/**
 * hutool工具复制文件
 */
public class StaticFileGenerator {

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
