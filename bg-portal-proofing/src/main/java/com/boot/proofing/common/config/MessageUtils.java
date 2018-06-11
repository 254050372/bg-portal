package com.boot.proofing.common.config;/**
 * @description
 * @autor xbwu on 2018/1/23.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 国际化消息读取工具
 * @author xbwu
 * @create 2018-01-23 
 **/
@Configuration
public class MessageUtils {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code, Object[] args, String defaultMessage) {
        String msg = messageSource.getMessage(code, args, defaultMessage, null);
        return msg != null ? msg.trim() : msg;
    }
    public String getMessage(String code, Object[] args) {
        return getMessage(code, args, null);
    }
    public String getMessage(String code) {
        return getMessage(code, null,null);
    }
    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
