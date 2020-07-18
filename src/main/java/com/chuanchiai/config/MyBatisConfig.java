package com.chuanchiai.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by chuanchiai on 2020/7/18
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.chuanchiai.dao")
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean  factoryBean = new SqlSessionFactoryBean();
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        factoryBean.setDataSource(dynamicDataSource);
        return  factoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
