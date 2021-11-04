package com.my.dynamic.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

public class TemplateExpUtil {


    /**
     * @param exportPath 文件输出地址
     * @param fileName   模板名称
     * @param model      POJO数据
     */
    public static void exportTxt(String exportPath, String fileName, Map<String, Object> model) {
        try {
            Configuration configuration = new Configuration(Configuration.getVersion());
            configuration.setClassForTemplateLoading(TemplateExpUtil.class, "/templates");
            configuration.setDefaultEncoding("UTF-8");
            //防止出现值为null的问题
            configuration.setClassicCompatible(true);

            //获取模板
            Template template = configuration.getTemplate(fileName);
            FileWriter writer = new FileWriter(new File(exportPath));
            template.process(model, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
