package com.boot.proofing.common.filter;/**
 * @description
 * @autor xbwu on 2017/8/14.
 */

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * druid过滤器
 * @author xbwu
 * @create 2017-08-14 
 **/
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",

        initParams={

                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源

        }

)
public class DruidStatFilter extends WebStatFilter {

}