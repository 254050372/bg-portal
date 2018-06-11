package com.boot.proofing.mpweb.mapper;/**
 * @description
 * @autor xbwu on 2018/5/25.
 */

/**
 *
 * @author xbwu
 * @create 2018-05-25 
 **/
import com.boot.core.dao.SuperMapper;
import com.boot.proofing.mpweb.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.annotations.SqlParser;

import java.util.List;
import java.util.Map;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<User> {

    /**
    *  删除所有
     * 自定义注入方法，sql写在mapper.xml中
     */
    int deleteAll();

    //可以直接把查询语句写在这里面
    @Select("select test_id as id, name from user")
    List<User> selectListBySQL();

    /**
     *  账号模糊查找
     * 自定义注入方法，sql写在mapper.xml中
     */
    List<User> selectByAccountLike(@Param("params")Map params);

}