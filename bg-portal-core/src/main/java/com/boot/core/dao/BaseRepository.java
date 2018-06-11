package com.boot.core.dao;

import com.boot.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @description jpa Repository接口
 * @autor xbwu on 2017/8/23.
 */
//中间Repository接口注解，避免中间Repository接口的方法被Spring创建实例
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {

}
