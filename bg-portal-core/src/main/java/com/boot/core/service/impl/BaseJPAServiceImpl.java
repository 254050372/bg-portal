package com.boot.core.service.impl;/**
 * @description
 * @autor xbwu on 2017/11/14.
 */


import com.boot.core.dao.BaseRepository;
import com.boot.core.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import com.boot.core.service.BaseJPAService;
import java.util.List;

/**
 * 基础服务实现
 * @author xbwu
 * @create 2017-11-14 
 **/
public abstract class BaseJPAServiceImpl<ENTITY extends BaseEntity> implements BaseJPAService<ENTITY> {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * spring 4 新特性：泛型式依赖注入
     */
    @Autowired
    protected BaseRepository<ENTITY,Long> repository;

    @Override
    public List<ENTITY> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ENTITY> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public List<ENTITY> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public <S extends ENTITY> List<S> saveAll(Iterable<S> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void flush() {
        repository.flush();
    }

    @Override
    public <S extends ENTITY> S saveOrUpdateAndFlush(S entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public <S extends ENTITY> S saveOrUpdate(S entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteInBatch(Iterable<ENTITY> baseEntities) {
        repository.deleteInBatch(baseEntities);
    }

    @Override
    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(ENTITY entity) {
        repository.delete(entity);
    }

    @Override
    public ENTITY getOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public <S extends ENTITY> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }

    @Override
    public <S extends ENTITY> List<S> findAll(Example<S> example, Sort sort) {
        return repository.findAll(example,sort);
    }

    @Override
    public Page<ENTITY> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public PageRequest buildPageRequest(int pageNumber, int pagzSize,Sort sort) {
        return PageRequest.of(pageNumber - 1, pagzSize, sort);
    }
}
