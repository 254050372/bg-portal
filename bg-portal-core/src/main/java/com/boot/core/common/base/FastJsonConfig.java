package com.boot.core.common.base;/**
 * @description
 * @autor xbwu on 2017/8/17.
 */


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * fastjson代替boot @ResponseBody输出配置
 * 因有的返回结果可能是xml格式或者其他非json格式，所以不启全局配置
 * @author xbwu
 * @create 2017-08-17
 **/
//@Configuration
public class FastJsonConfig {
    protected FastJsonConfig() {
    }

    //@Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        com.alibaba.fastjson.support.config.FastJsonConfig fastJsonConfig = new com.alibaba.fastjson.support.config.FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue
        );
        ValueFilter valueFilter = new ValueFilter() {
            //o 是class
            //s 是key值
            //o1 是value值
            public Object process(Object o, String s, Object o1) {
                if (null == o1){
                    o1 = "";
                }
                return o1;
            }
        };
        fastJsonConfig.setSerializeFilters(valueFilter);
        //可以设置编码，默认UTF-8
        //fastJsonConfig.setCharset(Charset.forName("UTF-8"));

        converter.setFastJsonConfig(fastJsonConfig);

        return converter;
    }
}