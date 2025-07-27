package com.yupi.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.yupi.model.MainTemplateConfig;
import picocli.CommandLine;

import java.lang.reflect.Field;

@CommandLine.Command(name = "ConfigCommand",description = "打印用户输入（选择）的动态参数的信息",mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{

    @Override
    public void run() {
        System.out.println("打印参数信息");
        //1.通过反射获取要打印的字段信息
//        Class<MainTemplateConfig> configClass = MainTemplateConfig.class;
//        Field[] fields = configClass.getDeclaredFields();
        //2.hutool工具
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        for (Field feild : fields) {
            System.out.println("字段名称2："+feild.getName());
            System.out.println("字段类型2："+feild.getType());
            System.out.println("-------");
        }
    }
}
