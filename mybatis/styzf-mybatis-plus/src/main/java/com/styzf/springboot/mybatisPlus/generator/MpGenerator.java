package com.styzf.springboot.mybatisPlus.generator;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 代码生成器演示
 * </p>
 * 
 * @author styzf
 * @date 2018年7月23日 
 *
 */
public class MpGenerator {

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void generator(String path, String jdbcUrl, String user, String passwork, 
            String parentPackage, String moduleName, 
            String[] includeTable, String excludeTable) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(path);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("styzf");

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(user);
        dsc.setPassword(passwork);
        dsc.setUrl(jdbcUrl);
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        if (includeTable != null && includeTable.length > 0) {
            strategy.setInclude(new String[] { "user" }); // 需要生成的表
        }
        if (excludeTable != null && excludeTable.length() > 0) {
            strategy.setExclude(new String[]{"test"}); // 排除生成的表
        }
        // 自定义实体父类
         strategy.setSuperEntityClass("com.styzf.springboot.mybatisPlus.entity.BaseEntity");
        // 自定义 mapper 父类
         strategy.setSuperMapperClass("com.styzf.springboot.mybatisPlus.mapper.BaseMapper");
        // 自定义 service 父类
         strategy.setSuperServiceClass("com.baomidou.mybatisplus.service.IService");
        // 自定义 service 实现类父类
         strategy.setSuperServiceImplClass("com.baomidou.mybatisplus.service.impl.ServiceImpl");
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage);
        pc.setModuleName(moduleName);
        mpg.setPackageInfo(pc);
        // xml生成
        TemplateConfig tc = new TemplateConfig();
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
    }

}
