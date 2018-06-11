package com.boot.proofing.mpweb.service.impl;/**
 * @description
 * @autor xbwu on 2018/5/25.
 */

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Condition;
import com.boot.core.service.impl.BaseMPServiceImpl;
import com.boot.proofing.mpweb.entity.User;
import com.boot.proofing.mpweb.mapper.UserMapper;
import com.boot.proofing.mpweb.service.IUserService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service("userServiceMP")
public class UserServiceImpl extends BaseMPServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public boolean deleteAll() {
        return retBool(baseMapper.deleteAll());
    }

    @Override
    public List<User> selectByAccountLike(Map params) {
        return baseMapper.selectByAccountLike(params);
    }

    @Override
    public List<User> selectListBySQL() {
        return baseMapper.selectListBySQL();
    }

    @Override
    public User findByUserAccount(String account) {
        List<User> list=baseMapper.selectList(Condition.create().and().eq("account",account));
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}