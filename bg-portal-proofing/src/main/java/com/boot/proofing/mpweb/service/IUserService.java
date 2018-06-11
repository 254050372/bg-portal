package com.boot.proofing.mpweb.service;/**
 * @description
 * @autor xbwu on 2018/5/25.
 */
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.boot.core.service.BaseMPService;
import com.boot.proofing.mpweb.entity.User;

/**
 * 用户服务
 * @author xbwu
 * @create 2018-05-25 
 **/
/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends BaseMPService<User> {

    //范例
    boolean deleteAll();
    List<User> selectByAccountLike(Map params);
    List<User> selectListBySQL();

    /**
     * 根据账号查找用户
     * @param account
     * @return
     */
    User findByUserAccount(String account);
}