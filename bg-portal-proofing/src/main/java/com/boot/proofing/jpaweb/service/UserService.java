package com.boot.proofing.jpaweb.service;/**
 * @description
 * @autor xbwu on 2017/8/12.
 */

import com.boot.core.service.BaseJPAService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.boot.proofing.jpaweb.entity.User;

/**
 * 用户服务
 * @author xbwu
 * @create 2017-08-12 
 **/
public interface UserService extends BaseJPAService<User> {

    Page<User> findPageByUserAccount(String account, Pageable pageable);

    User findByUserAccount(String account);
}
