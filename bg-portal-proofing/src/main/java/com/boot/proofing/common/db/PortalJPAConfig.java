package com.boot.proofing.common.db;/**
 * @description
 * @autor xbwu on 2017/8/12.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * portal本地jpa+连接池配置
 * @author xbwu
 * @create 2017-08-12
 **/
@Configuration
//此处是你dao文件所在的包名
@EnableJpaRepositories(PortalJPAConfig.PACKAGE)
//通知Spring，@Transactional注解的类被事务的切面包围。这样@Transactional就可以使用了
@EnableTransactionManagement
//启用Springdata支持
@EnableSpringDataWebSupport
public class PortalJPAConfig {
    // 精确到 本地 目录，以便跟其他数据源隔离(mapper接口扫描地址)
    static final String PACKAGE = "com.boot.**.dao";

    @Autowired
    @Qualifier("portalDataSource")
    private DataSource dataSource;

    @Bean
    @Primary
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        //设置java bean所在的包名
        factory.setPackagesToScan("com.boot.**.entity.**");
        factory.setDataSource(dataSource);

        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
        jpaProperties.put("hibernate.jdbc.batch_size",50);
        //自动建表语句
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");

        //数据库方言设置，不设置的话跟druid的密码加密功能会冲突
        jpaProperties.put("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");

        factory.setJpaPropertyMap(jpaProperties);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}
