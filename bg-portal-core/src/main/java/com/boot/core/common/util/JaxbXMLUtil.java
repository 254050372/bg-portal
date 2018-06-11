package com.boot.core.common.util;/**
 * @description
 * @autor xbwu on 2017/8/18.
 */


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * 实体与xml相互转换
 * @author xbwu
 * @create 2017-08-18 
 **/
public class JaxbXMLUtil {
    /**
     * java对象转换为xml文件
     * @param obj  对象
     * @param load  java对象.Class
     * @return    xml文件的String
     * @throws JAXBException
     */
    public static String beanToXml(Object obj,Class<?> load) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        //决定是否在转换成xml时同时进行格式化（即按标签自动换行，否则即是一行的xml）
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //xml的编码方式
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj,writer);
        return writer.toString();
    }
    /**
     * xml转换成JavaBean
     * @param xml
     * @param c
     * @return
     */
    public static <T> T xmlToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
