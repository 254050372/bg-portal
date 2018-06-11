package com.boot.core.common.util;/**
 * @description
 * @autor xbwu on 2018/1/23.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 本地国际化信息读取
 *
 * @author xbwu
 * @create 2018-01-23
 **/
@Component
public class LocaleMessageSource {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageSource messageSource;


    public String getMsg(String code, Object[] args, String defaultMessage) {
        return messageSource.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }
    public String getMsg(String code,String defaultMessage) {
        return messageSource.getMessage(code, null, defaultMessage, LocaleContextHolder.getLocale());
    }
    public String getMsg(String code, Object[] args) {
        try {
            return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return "";
        } catch (Exception e) {
            logger.error("messageSource.getMessage error,code {}", code, e);
            return "";
        }
    }

    public String getMsg(String code) {
        try {
            return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return "";
        } catch (Exception e) {
            logger.error("messageSource.getMessage error,code {}", code, e);
            return "";
        }
    }
}
