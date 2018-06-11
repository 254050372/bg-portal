package com.boot.proofing.common.db;/**
 * @description
 * @autor xbwu on 2018/5/25.
 */

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * mybatis plus注入公共字段自动填充,任选注入方式即可
 * @author xbwu
 * @create 2018-05-25 
 **/
public class MyMetaObjectHandler extends MetaObjectHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void insertFill(MetaObject metaObject) {
        //获取当前登录用户插入--TODO
//        setFieldValByName("operator", null,metaObject);
        setFieldValByName("createDateTime", new Date(),metaObject);
        setFieldValByName("modifyDateTime", new Date(),metaObject);
        //默认逻辑删除值为1，就是未被删除的意思
        setFieldValByName("isDelete", "1",metaObject);
        //是否有效设置
        Boolean valid=(Boolean) getFieldValByName("valid",metaObject);
        if(valid==null){
            setFieldValByName("valid", true,metaObject);
        }
        //乐观锁初始值
        setFieldValByName("version", 0L,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //获取当前登录用户插入--TODO
//        setFieldValByName("operator", null,metaObject);
        setFieldValByName("modifyDateTime", new Date(),metaObject);

    }
}