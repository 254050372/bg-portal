package com.boot.proofing.jpaweb.dao;/**
 * @description
 * @autor xbwu on 2017/8/12.
 */

import com.boot.core.dao.BaseRepository;
import com.boot.proofing.jpaweb.entity.UserInfo;
import org.springframework.stereotype.Repository;


/**
 * 用户信息
 * @author xbwu
 * @create 2017-08-12 
 **/
@Repository
public interface UserInfoRepo extends BaseRepository<UserInfo,Long> {

}
