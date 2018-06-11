package com.boot.proofing.common.config;/**
 * @description
 * @autor xbwu on 2018/1/24.
 */

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 常量配置
 * 使用方法：在普通类中直接调用SystemConfig.author的获取
 *
 * 如果常量仅少数地方使用，可以在某些类中采取以下方式进行注入获取：
 * @Value("system.author")
 * private String systemAuthor;
 *
 * 当明确需要在本类中添加系统变量时，需要定义:
 * 1.静态属性名，形如 public static String author;
 * 2.属性名的公开getset方法，方法不需要标记静态static
 *      形如：public String getAuthor() {return author;}public void setAuthor(String author) {this.author = author;}
 *
 * @author xbwu
 * @create 2018-01-24 
 **/
//Configuration与ConfigurationProperties需配合一起使用，否则无法注入
@Configuration
//用来指定额外的配置文件位置和编码
@PropertySource(value = "classpath:config/baseConstant.properties",encoding = "utf-8")
@ConfigurationProperties(prefix = "boot.base")
public class BaseConstant {
    /**
     * 系统作者
     */
    public static String author;
    /**
     * 当前版本
     */
    public static String version;
    /**
     * 模板版本
     */
    public static String templatesPrefix;

    public void setVersion(String version) {
        this.version = version;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTemplatesPrefix(String templatesPrefix) {
        this.templatesPrefix = templatesPrefix;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
