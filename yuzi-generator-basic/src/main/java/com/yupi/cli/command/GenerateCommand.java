package com.yupi.cli.command;
 
import cn.hutool.core.bean.BeanUtil;
import com.yupi.generator.MainGenerator;
import com.yupi.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine.*;

import java.util.concurrent.Callable;

/**
 * 因为loop等字段都是私有的，所以必须通过get、set方法复制
 * 解决办法：1.加@Data注解
 *          2.修饰符改为public
 */
@Command(name = "GenerateCommand",description = "生成源代码文件",mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
    
    @Option(names = {"-l","--loop"},arity = "0..1",description = "是否循环",interactive = true)
//    public boolean loop;
    private boolean loop;

    @Option(names = {"-a","--author"},arity = "0..1",description = "作者",interactive = true)
    private String author = "方世玉";

    @Option(names = {"-o","--outputText"},arity = "0..1",description = "输出文本",interactive = true)
    private String outputText = "sum = ";
    @Override
    public Integer call() throws Exception {
        MainTemplateConfig templateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this,templateConfig);
        MainGenerator.doGenerator(templateConfig);
        System.out.println("配置信息："+templateConfig);
        return 0;
    }
}
