package com.aaa.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

/**
 * @Author: 陈建
 * @Date: 2020/6/9 0009 15:10
 * @Version 1.0
 * 使用mybatisplus自动生成代码
 */
public class MyGenerator {
    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("D://生成代码//");//输出目录
        globalConfig.setFileOverride(true);// 是否覆盖文件
        globalConfig.setActiveRecord(true);// 开启 activeRecord 模式
        globalConfig.setEnableCache(false);// XML 二级缓存
        globalConfig.setBaseResultMap(true);// XML ResultMap
        globalConfig.setBaseColumnList(true);// XML columList
        globalConfig.setAuthor("AAATeacherChen");//作者
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        globalConfig.setMapperName("%sDao");
        globalConfig.setXmlName("%sDao");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        mpg.setGlobalConfig(globalConfig);
        DataSourceConfig sourceConfig = new DataSourceConfig();
        sourceConfig.setDbType(DbType.MYSQL);// 数据库类型
        sourceConfig.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });
        sourceConfig.setDriverName("com.mysql.jdbc.Driver");
        sourceConfig.setUsername("root");
        sourceConfig.setPassword("root");
        sourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/teacher?characterEncoding=utf8");
        mpg.setDataSource(sourceConfig);
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // .setCapitalMode(true)// 全局大写命名
        strategyConfig.setTablePrefix(new String[]{"tbl_"});// 此处可以修改为您的表前缀
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        mpg.setStrategy(strategyConfig);
        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.aaa");// 自定义包路径
        packageConfig.setController("controller");// 这里是控制器包名，默认 web
        packageConfig.setMapper("dao");
        packageConfig.setEntity("entity");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        mpg.setPackageInfo(packageConfig);
        // 注入配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        injectionConfig.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                return globalConfig.getOutputDir() + tableInfo.getEntityName() + "Dao.xml";
            }
        }));
        mpg.setCfg(injectionConfig);
        // 执行生成*/
        mpg.execute();
    }
}
