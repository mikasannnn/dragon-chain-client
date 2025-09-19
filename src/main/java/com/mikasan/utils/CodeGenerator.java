package com.mikasan.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * CodeGenerator
 *
 * @author MiKaSan
 * @Description :
 * @since 2025/8/22 20:26
 */
public class CodeGenerator {
    public static void main(String[] args) {
        generate();
    }

    private static void generate() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/dragon_chain", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("mikasan") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\Mine\\BlockChain\\DragonChain\\client\\dragon-chain-client\\dragon-chain-client\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.mikasan") // 设置父包名
                            .moduleName(null) // 设置父包模块名（空字符串""，地址会变成//）
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Mine\\BlockChain\\DragonChain\\client\\dragon-chain-client\\dragon-chain-client\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
//                builder.mapperBuilder().enableMapperAnnotation().build();  MybatisPlusCong.java文件里加过MapperScan，这里就不需要开启
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("resource_reports","traffic_reports") // 设置需要生成的表名
                            .addTablePrefix(); // 设置过滤表前缀
                })
//            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
