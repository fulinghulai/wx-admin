package com.hoolai.bi.wxadmin.conf;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-02 09:21
 * Description:
 */
@Configuration
@MapperScan(basePackages = "com.hoolai.bi.wxadmin.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DruidDataSourceConfig {

	/**
	 * 配置mapper的扫描，找到所有的mapper.xml映射文件
	 */
	@Value("${mybatis-plus.mapper-locations}")
	private String mapperLocations;

	/**
	 * 数据源1
	 */
	@Bean(name = "oneDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.one")
	public DataSource dataSourceOne() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * 数据源2
	 */
	@Bean(name = "twoDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.two")
	public DataSource dataSourceTwo() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * 数据源3
	 */
	@Bean(name = "threeDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.three")
	public DataSource dataSourceThree() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * 数据源管理
	 */
	@Bean
	public DataSource dynamicDataSource() throws SQLException {
		DynamicDataSource dynmicDataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("oneDataSource", dataSourceOne());
		targetDataSources.put("twoDataSource", dataSourceTwo());
		targetDataSources.put("threeDataSource", dataSourceThree());
		dynmicDataSource.setTargetDataSources(targetDataSources);
		dynmicDataSource.setDefaultTargetDataSource(dataSourceOne());  //设置默认数据源
		return dynmicDataSource;
	}

	/**
	 * SqlSessionFactory
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
		MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();  //兼容Mybatis-plus的接口
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		return bean.getObject();
	}

	/**
	 * 事物
	 */
	@Bean
	public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
