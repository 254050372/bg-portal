package com.boot.proofing.jpaweb.dao;/**
 * @description
 * @autor xbwu on 2017/8/12.
 */

import com.boot.core.dao.BaseRepository;
import com.boot.proofing.jpaweb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户
 * @author xbwu
 * @create 2017-08-12 
 **/
@Repository
public interface UserRepo extends BaseRepository<User,Long> {

    /**
     * 根据账号分页查询
     * 默认使用hibernate 的hql写法，可以在注解@Query中指定使用sql语句解析，nativeQuery=true
     * @param account
     * @param pageable
     * @return
     */
    @Query("select a from User a where a.account=:account")
    Page<User> findPageByUserAccount(@Param("account") String account, Pageable pageable);

    @Query("select a from User a where a.account=:account")
    List<User> findByUserAccount(@Param("account") String account);
}
