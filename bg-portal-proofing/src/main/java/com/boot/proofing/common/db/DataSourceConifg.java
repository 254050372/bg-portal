package com.boot.proofing.common.db;/**
 * @description
 * @autor xbwu on 2017/9/28.
 */

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数据源配置
 * @author xbwu
 * @create 2017-09-28 
 **/
//Spring 配置文件注解
@Configuration
public class DataSourceConifg {

    //主要数据源注解，必须指定一个,mybatis plus也是默认使用此数据源
    @Primary
    //数据源名称
    @Bean(name="portalDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.local")
    public DataSource dataSourcePortal(){
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name="bpmDataSource")
    @ConfigurationProperties(prefix ="spring.datasource.druid.bpm")
    public DataSource dataSourceBPM(){
        return DruidDataSourceBuilder.create().build();
    }
}
