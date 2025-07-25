import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * 全局配置对象
 */
public class FreeMarkerTest {

    @Test
    public void test() throws IOException, TemplateException {
        //指定版本
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        //指定模板文件的目录路径
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/template"));
        //设置编码
        cfg.setDefaultEncoding("UTF-8");
        //删除数字中的分隔符，
        cfg.setNumberFormat("0.####");
        //添加模板并获取
        Template template = cfg.getTemplate("myweb.html.ftl");

        //准备数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear",2023);
        List<Map<String, Object>> menuItems = new ArrayList<>();
        Map<String, Object> item = new HashMap<>();
        item.put("url","http://www.baidu.com");
        item.put("label","百度一下");
        Map<String, Object> item2 = new HashMap<>();
        item2.put("url","http://www.qq.com");
        item2.put("label","腾讯网");
        menuItems.add(item);
        menuItems.add(item2);
        dataModel.put("menuItems",menuItems);

        //指定生成文件
        FileWriter out = new FileWriter("myweb.html");
        //开始生成
        template.process(dataModel,out);
        out.close();
    }
}
