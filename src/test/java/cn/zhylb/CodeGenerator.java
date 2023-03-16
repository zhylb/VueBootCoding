package cn.zhylb;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.platform.commons.util.ModuleUtils;

import java.util.Collections;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * @date : 2023/3/14 15:04
 */
public class CodeGenerator {
    public static void main(String[] args) {


        String url = "jdbc:mysql:///xdb";
        String username = "root";
        String password = "abc123";
        String author = "zhylb";

        String outPutDir = "D:\\chromeDownload\\JavaCode\\VueBoot\\src\\main\\java";


        String parent = "cn.zhylb";
        String moduleName = "boot";

        String xmlPath = "D:\\chromeDownload\\JavaCode\\VueBoot\\src\\main\\resources\\mapper\\";

        String tableName = "x_user,x_menu,x_role,x_role_menu,x_user_role";
        String tablePrefix = "x_";


        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                           // .enableSwagger() // 开启 swagger 模式
                           // .fileOverride() // 覆盖已生成文件
                            .outputDir(outPutDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parent) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, xmlPath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置需要生成的表名
                            .addTablePrefix(tablePrefix); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
