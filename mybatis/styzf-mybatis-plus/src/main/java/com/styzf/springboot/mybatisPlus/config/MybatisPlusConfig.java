package com.styzf.springboot.mybatisPlus.config;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.styzf.springboot.mybatisPlus.dataSource.P6DataSourceClusterConfig;

@Configuration
public class MybatisPlusConfig {

    @Autowired
	private P6DataSourceClusterConfig dsConfig;
	
	@Autowired
	private MybatisProperties properties;
	
	@Autowired
	private ResourceLoader resourceLoader = new DefaultResourceLoader();
    
	@Autowired(required = false)
	private Interceptor[] interceptors;
	
	@Autowired(required=false)
	private DatabaseIdProvider databaseIdProvider;
	
	/**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    @DependsOn({"dataSoure"})
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType(dsConfig.getPlatform());// 配置方言
        return paginationInterceptor;
//        paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
//        paginationInterceptor.setDialectType("");//设置方言类型
        /*
         * 【测试多租户】 SQL 解析处理拦截器<br>
         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
         */
      /*  List<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId() {
                return new LongValue(1L);
            }

            @Override
            public String getTenantIdColumn() {
                return "tenant_id";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 这里可以判断是否过滤表
                
                if ("user".equals(tableName)) {
                    return true;
                }
                return false;
            }
        });


        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);*/
        // 以下过滤方式与 @SqlParser(filter = true) 注解等效
//        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
//            @Override
//            public boolean doFilter(MetaObject metaObject) {
//                MappedStatement ms = PluginUtils.getMappedStatement(metaObject);
//                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
//                if ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId())) {
//                    return true;
//                }
//                return false;
//            }
//        });
//        return paginationInterceptor;
    }

	@Bean
	public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
	  MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
	  mybatisPlus.setDataSource(dsConfig.dataSource());
	  mybatisPlus.setVfs(SpringBootVFS.class);
	  if (StringUtils.hasText(properties.getConfigLocation())) {
	    mybatisPlus.setConfigLocation(resourceLoader.getResource(properties.getConfigLocation()));
	  }
	  mybatisPlus.setConfiguration(properties.getConfiguration());
	  if (!ObjectUtils.isEmpty(interceptors)) {
	    mybatisPlus.setPlugins(interceptors);
	  }
	  
	  GlobalConfiguration globalConfig = new GlobalConfiguration();
	  globalConfig.setDbType(DBType.MYSQL.name());
	  
	  globalConfig.setIdType(IdType.ID_WORKER.getKey());
//	  globalConfig.setIdType(IdType.AUTO.getKey());
	  mybatisPlus.setGlobalConfig(globalConfig);
	  MybatisConfiguration mc = new MybatisConfiguration();
	  mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
	  mybatisPlus.setConfiguration(mc);
	  if (databaseIdProvider != null) {
	    mybatisPlus.setDatabaseIdProvider(databaseIdProvider);
	  }
	  if (StringUtils.hasLength(properties.getTypeAliasesPackage())) {
	    mybatisPlus.setTypeAliasesPackage(properties.getTypeAliasesPackage());
	  }
	  if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
	    mybatisPlus.setTypeHandlersPackage(properties.getTypeHandlersPackage());
	  }
	  if (!ObjectUtils.isEmpty(properties.resolveMapperLocations())) {
	    mybatisPlus.setMapperLocations(properties.resolveMapperLocations());
	  }
	  return mybatisPlus;
	}
    
    
//    @Bean
//    public MetaObjectHandler metaObjectHandler(){
//        return new MyMetaObjectHandler();
//    }

    /**
     * 注入主键生成器
     */
    @Bean
    public IKeyGenerator keyGenerator(){
        return new H2KeyGenerator();
    }

    /**
     * 注入sql注入器
     */
//    @Bean
//    public ISqlInjector sqlInjector(){
//        return new LogicSqlInjector();
//    }

}
