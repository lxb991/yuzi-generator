package ${basePackage}.cli.command;

import cn.hutool.core.util.ReflectUtil;
import ${basePackage}.model.DataModel;
import picocli.CommandLine;

import java.lang.reflect.Field;

@CommandLine.Command(name = "ConfigCommand",description = "打印用户输入（选择）的动态参数的信息",mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{

    @Override
    public void run() {
        System.out.println("打印参数信息");
        //2.hutool工具
        Field[] fields = ReflectUtil.getFields(DataModel.class);
        for (Field feild : fields) {
            System.out.println("字段名称2："+feild.getName());
            System.out.println("字段类型2："+feild.getType());
            System.out.println("-------");
        }
    }
}
