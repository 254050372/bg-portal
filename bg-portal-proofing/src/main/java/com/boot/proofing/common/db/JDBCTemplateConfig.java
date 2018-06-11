package com.boot.proofing.common.db;/**
 * @description
 * @autor xbwu on 2017/8/9.
 */

import com.boot.core.common.base.JdbcQueryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * jdbcTamplate配置
 * @author xbwu
 * @create 2017-08-09 
 **/
@Configuration
public class JDBCTemplateConfig {

    /**
     * portal本地连接
     * @param dataSource
     * @return
     */
    @Bean(name = "localJdbcTemplate")
    public JdbcTemplate localJdbcTemplate(@Qualifier("portalDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    /**
     * bpm连接,目前连接已被限制，不要使用
     * @param dataSource
     * @return
     */
    @Bean(name = "bpmJdbcTemplate")
    public JdbcTemplate bpmJdbcTemplate(@Qualifier("bpmDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    /**
     * 注入mysql分页查询
     * @param jdbcTemplate
     * @return
     */
    @Bean(name="localJQB")
    public JdbcQueryBuilder localJQB(@Qualifier("localJdbcTemplate")JdbcTemplate jdbcTemplate){
        return new JdbcQueryBuilder(jdbcTemplate);
    }

    /**
     * 注入oracle分页查询
     * @param jdbcTemplate
     * @return
     */
    @Bean(name="bpmJQB")
    public JdbcQueryBuilder bpmJQB(@Qualifier("bpmJdbcTemplate") JdbcTemplate jdbcTemplate){
        return new JdbcQueryBuilder(jdbcTemplate);
    }

}
