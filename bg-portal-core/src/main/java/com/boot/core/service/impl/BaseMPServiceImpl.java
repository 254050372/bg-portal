package com.boot.core.service.impl;/**
 * @description
 * @autor xbwu on 2018/6/1.
 */

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * mybatis plus服务实现拓展接口
 * @author xbwu
 * @create 2018-06-01 
 **/
public class BaseMPServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> {
}
