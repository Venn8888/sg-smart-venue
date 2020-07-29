package com.sg;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author dwf
 * @date 2020/7/29 21:58
 */

public class CodeGennerator {
    public static void main(String[] args) {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        //是否支持AR模式
        config.setActiveRecord(true)
                .setAuthor("venn") //作者
                .setOutputDir("D:\\Venn\\IdeaProjects\\sg-smart-venue\\smart-gennerator")  //生成路径
                .setFileOverride(true)//是否文件覆盖，如果多次
                .setServiceName("%sFacade") //设置生成的service接口名首字母是否为I
                .setIdType(IdType.AUTO) //主键策略
                .setServiceImplName("%sFacadeImpl")//设置生成的service接口的名字的首字母是否为I
                .setEntityName("%sDomain")
                .setBaseResultMap(true)
                .setBaseColumnList(true);
        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://venn8888.cn:3333/platform-base")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("123456");
        //3.策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) // 全局大写命名
                //.setDbColumnUnderline(true) //表名 字段名 是否使用下滑线命名
                .setEntityLombokModel(true)
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setInclude("sys_account_log","sys_account_org","sys_account_role","sys_authority","sys_data_authority","sys_data_dimension","sys_data_domain","sys_dimension","sys_field_authority","sys_org","sys_role","sys_role_authority","sys_user_account") //生成的表
                .setInclude()
                .setTablePrefix("sys_"); // 表前缀
        //4.包名策略
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("")//父包名
                .setController("com.sg.system.controller")
                .setEntity("com.sg.system.domain")
                .setService("com.sg.system.facade")
                .setServiceImpl("com.sg.system.facade.impl")
                .setMapper("com.sg.system.mapper")
                .setXml("mapper.system");
        //5.整合配置
        AutoGenerator ag = new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        ag.execute();
    }
}
