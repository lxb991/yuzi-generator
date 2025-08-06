package ${basePackage}.cli.command;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.generator.MainGenerator;
import ${basePackage}.model.DataModel;
import lombok.Data;
import picocli.CommandLine.*;

import java.util.concurrent.Callable;

/**
 * 因为loop等字段都是私有的，所以必须通过get、set方法复制
 * 解决办法：1.加@Data注解
 *          2.修饰符改为public
 * echo = true 命令行回显用户的输入
 */
@Command(name = "GenerateCommand",description = "生成源代码文件",mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
<#list modelConfig.models as modelInfo>


    @Option(names = {<#if modelInfo.abbr??>"-${modelInfo.abbr}",</#if>"--${modelInfo.fieldName}"},arity = "0..1",<#if modelInfo.description??> description = "${modelInfo.description}",</#if>interactive = true,echo = true)
    private ${modelInfo.type} ${modelInfo.fieldName} <#if modelInfo.defaultValue??>=${modelInfo.defaultValue?c}</#if>;
</#list>

    @Override
    public Integer call() throws Exception {
        DataModel templateConfig = new DataModel();
        BeanUtil.copyProperties(this,templateConfig);
        MainGenerator.doGenerator(templateConfig);
        System.out.println("配置信息："+templateConfig);
        return 0;
    }
}
