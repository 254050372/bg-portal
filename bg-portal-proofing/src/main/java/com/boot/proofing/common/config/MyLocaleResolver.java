package com.boot.proofing.common.config;/**
 * @description
 * @autor xbwu on 2018/1/22.
 */

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自动国际化配置
 * @author xbwu
 * @create 2018-01-22 
 **/
public class MyLocaleResolver extends AcceptHeaderLocaleResolver {

    private Locale myLocal;
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        return myLocal==null?request.getLocale():myLocal;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        myLocal = locale;
    }
}