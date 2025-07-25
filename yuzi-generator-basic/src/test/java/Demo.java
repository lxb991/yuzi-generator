import cn.hutool.core.util.ArrayUtil;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Demo {
    public static void main(String[] args) throws IOException {
        String proPath = System.getProperty("user.dir");
//        System.out.println(proPath);
        File parentFile = new File(proPath).getParentFile();
        String srcPath = parentFile + File.separator + "yuzi-generator-demo-projects" + File.separator + "acm-template";
        String destPath = proPath;
        copyFilesByRecursive(srcPath,destPath);
    }

    public static void copyFilesByRecursive(String srcPath,String destPath) throws IOException {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        copyFileByRecursive(srcFile,destFile);
    }

    public static void copyFileByRecursive(File srcFile,File destFile) throws IOException {
        if (srcFile.isDirectory()){
            File newDestFile = new File(destFile, srcFile.getName());
            if (!newDestFile.exists()){
                newDestFile.mkdirs();
            }
            File[] files = srcFile.listFiles();
            if (ArrayUtil.isEmpty(files)){
                return;
            }
            for (File file : files) {
                copyFileByRecursive(file,newDestFile);
            }
        }else {
            Path newDestPath = destFile.toPath().resolve(srcFile.getName());
            Files.copy(srcFile.toPath(),newDestPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
