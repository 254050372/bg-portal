package com.boot.proofing.web.controller;/**
 * @description
 * @autor xbwu on 2018/5/25.
 */

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.boot.proofing.ProofingApplicationTests;
import com.boot.proofing.mpweb.entity.User;
import com.boot.proofing.mpweb.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ORM： mybatis plus 测试类
 *
 * @author xbwu
 * @create 2018-05-25
 **/
@Transactional
public class TestMybatisPlusController extends ProofingApplicationTests {

    @Autowired
    IUserService userService;

    @Test
    public void TestUserSelect() {
        //mp分页查询
        Page<User> pu = userService.selectPage(new Page<User>(0, 12));
        //mp条件查询
        Condition uw = Condition.create();
        List<User> list = userService.selectList(uw.eq("username", "吴小彬").andNew().eq("account", "chixinzei"));
        Map<String,Object> params=new HashMap<>();
        params.put("account","吴小彬");
        //mybatis原始书写mapper.xml查询
        List<User> list1 = userService.selectByAccountLike(params);
        System.out.println(list.get(0));
        System.out.println(list1.get(0));
    }

    @Test
    //增删改，逻辑删除
    public void TestUserInsert() {
        Map<String,Object> param=new HashMap<>();
        param.put("account",53L);
        userService.selectByAccountLike(param);
        User u=new User();
        u.setAccount("test");
        u.setPassword("123");
        u.setUsername("小彬test");
        u.setValid(false);
        //新增修改
        userService.insert(u);
    }

    @Test
    //增删改，逻辑删除
    public void TestUserUpdateOrDelete() {
        User u=userService.selectOne(Condition.create().and().eq("id",53L));
        //更新
        Boolean isUpdate= userService.updateById(u);
        System.out.println(isUpdate?"更新成功！":"更新失败");
        Boolean isDelete=userService.deleteById(53L);
        System.out.println(isUpdate?"删除成功！":"删除失败");
    }
}
