package com.boot.core.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @description 基础jpa服务接口
 * @autor xbwu on 2017/9/28.
 */
public interface BaseJPAService<ENTITY> {

    List<ENTITY> findAll();

    List<ENTITY> findAll(Sort sort);

    List<ENTITY> findAllById(Iterable<Long> ids);

    <S extends ENTITY> List<S> saveAll(Iterable<S> entities);

    /**
     * 执行缓存与数据库的同步
     */
    void flush();

    /**
     * 保存并立即刷新缓存，保存与数据库同步
     * @param entity
     * @return
     */
    <S extends ENTITY> S saveOrUpdateAndFlush(S entity);

    <S extends ENTITY> S saveOrUpdate(S entity);

    void deleteInBatch(Iterable<ENTITY> entities);

    void deleteAllInBatch();

    void deleteById(Long id);

    void delete(ENTITY entity);

    ENTITY getOne(Long id);

    boolean existsById(Long id);

    <S extends ENTITY> List<S> findAll(Example<S> example);

    <S extends ENTITY> List<S> findAll(Example<S> example, Sort sort);

    Page<ENTITY> findAll(Pageable pageable);

}
