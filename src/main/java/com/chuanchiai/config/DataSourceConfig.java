package com.chuanchiai.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.chuanchiai.dynamicRouting.DynamicDataSource;
import com.chuanchiai.enums.DataSourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chuanchiai on 2020/7/18
 * 动态配置数据源
 */
@Configuration
public class DataSourceConfig {

    //主库数据源
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDb(){
        return DruidDataSourceBuilder.create().build();
    }

    //从库
    @Bean
    @ConditionalOnProperty(prefix = "spring.datasource",value = "slave",matchIfMissing =true)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDb(){
        return DruidDataSourceBuilder.create().build();
    }

    //动态数据源
    @Bean
    public DataSource dynamicDataSource(@Qualifier("masterDb")DataSource masterDb,
                                        @Autowired(required = false) @Qualifier("slaveDb")DataSource slaveDb){
        Map<Object,Object> targetDatasources = new HashMap<>();
        targetDatasources.put(DataSourceEnum.MASTER,masterDb);
        if (slaveDb!=null){
            targetDatasources.put(DataSourceEnum.SLAVE,slaveDb);
        }
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDatasources);
        dynamicDataSource.setDefaultTargetDataSource(masterDb);
        return  dynamicDataSource;
    }
}
