package com.boot.proofing.web.controller;/**
 * @description
 * @autor xbwu on 2017/8/9.
 */

import com.alibaba.fastjson.JSON;
import com.boot.core.common.asyn.TestAsynTask;
import com.boot.core.common.base.JdbcQueryBuilder;
import com.boot.core.common.base.Pages;
import com.boot.core.common.base.ResultWapper;
import com.boot.core.common.util.JaxbXMLUtil;
import com.boot.core.common.util.MD5Util;
import com.boot.core.common.util.UUIDGeneratorUtil;
import com.boot.proofing.ProofingApplicationTests;
import com.boot.proofing.jpaweb.dao.UserRepo;
import com.boot.proofing.jpaweb.entity.User;
import com.boot.proofing.jpaweb.entity.UserInfo;
import com.boot.proofing.jpaweb.service.UserInfoService;
import com.boot.proofing.jpaweb.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * ORM：SpringData JPA 测试类
 *
 * @author xbwu
 * @create 2017-08-09
 **/
@Transactional
public class TestSpringDataController extends ProofingApplicationTests {

    @Autowired
    @Qualifier("localJdbcTemplate")
    JdbcTemplate localJDBC;

    @Autowired
    @Qualifier("bpmJdbcTemplate")
    JdbcTemplate bpmJDBC;

    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    @Qualifier("localJQB")
    private JdbcQueryBuilder localJQB;

    @Autowired
    TestAsynTask testAsynTask;

//    @Autowired
//    @Qualifier("bpmJQB")
//    private JdbcQueryBuilder bpmJQB;


    @Test
    public void testLocal() {
        List<Map<String, Object>> list = localJDBC.queryForList("select * from t_user");
        logger.info(Arrays.asList(list).toString());
    }

    @Test
    //禁止回滚
    //@Rollback(value = false)
    public void testNewUserImportSSO() {
        String id = UUIDGeneratorUtil.getUUID();
        String userid = "adminBPM";
        String password = MD5Util.getMD5(userid);
        localJDBC.execute("insert into sso_user (id,account,password,valid) values('" + id + "','" + userid + "','" + password + "',1) ");
    }

    @Test
    public void testBPM() {
        List<Map<String, Object>> list = bpmJDBC.queryForList("SELECT * from users where rownum<2");
        logger.info(Arrays.asList(list).toString());
    }

    @Test
    public void testAJAXJSON() {
        List<Map<String, Object>> list = localJDBC.queryForList("select * from t_user");
        logger.info(JSON.toJSONString(ResultWapper.success("").addResult("list", list)));
    }

    @Test
    //禁止回滚
    @Rollback(value = false)
    public void testAJAXXML() throws Exception {
        //Pageable pageable = PageRequest.of(0, 10, new Sort(Sort.Direction.ASC, "id"));
        User us = new User();
        UserInfo ui = new UserInfo();
        ui.setEmail("254050372@qq.com");
        ui.setSex(1);
        us.setUserInfo(ui);
        us.setAccount("chixinzei");
        us.setPassword(MD5Util.getMD5("1234qwer"));
        us.setValid(true);
        userInfoService.saveOrUpdate(ui);
        userService.saveOrUpdate(us);
        logger.info("{},{}",us.getAccount(),us.getValid());
        logger.info(JaxbXMLUtil.beanToXml(us, User.class));
    }

    /**
     * jdbcTemplate分页查询，目前支持mysql和oracle，适用于复杂字段查询返回或者快速编写查询
     * 可直接返回对象类型，或者List<Map>类型
     *
     * @throws Exception
     */
    @Test
    public void testJDBCPage() throws Exception {
        Pages page = new Pages<>();
        //返回map
        List<Map<String, Object>> list = localJQB.queryPage("select *from t_user order by id", page);
        logger.info("{} ,总条数：{},总页数：{},是否存在下一页：{},是否存在上一页：{}",
            Arrays.asList(list).toString(),
            page.getTotalCount(),
            page.getTotalPages(),
            page.isHasNext(),
            page.isHasPre());
        Pages page1 = new Pages(1, 2);
        //返回单个对象
        User ue = localJQB.queryOneByObject("select *from t_user where id=?", new Object[]{"53"}, User.class);
        Map map = localJQB.queryOneByMap("select *from t_user where id=?", new Object[]{"53"});
        logger.info("ue:{}" , ue);
        logger.info("ue map:{}" , map);

        //返回对象list
        List<User> users = localJQB.queryPage("select *from t_user order by id", page1, User.class);
        logger.info("users:{}", users);
    }

    /**
     * jpa测试相关
     *
     * @throws Exception
     */
    @Test
    //禁止回滚
    @Rollback(value = false)
    public void testJPAUpdate() throws Exception {
        //完整对象查询测试，修改关联对象值
        User u = userService.getOne(53L);
        logger.info("旧email:{}" , u.getUserInfo().getEmail());
        u.getUserInfo().setEmail("dd:" + u.getUserInfo().getEmail());
        //userInfo的值成功被更新！
        userService.saveOrUpdate(u);
    }

    /**
     * jpa分页测试相关
     * 参考文档：http://www.codeweblog.com/%E5%85%A5%E9%97%A8%E5%B8%96-%E4%BD%BF%E7%94%A8-spring-data-jpa-%E7%AE%80%E5%8C%96-jpa-%E5%BC%80%E5%8F%91/
     *
     * @throws Exception
     */
    @Test
    public void testJPAPageQuery() throws Exception {
        int pageNum = 1;
        int pageSize = 1;
        //jpa对象分页查询,page从0开始第一页
        PageRequest pr = PageRequest.of(pageNum - 1, pageSize);
        Page<User> page = userRepo.findPageByUserAccount("chixinzei", pr);
        logger.info(page.getContent().toString());
    }

    /**
     * 异步多线程调用
     *
     * @throws Exception
     */
    @Test
    public void testAsyn() throws Exception {
        System.out.println("=====hello this is asyn call back api");
        Future<String> f = testAsynTask.doTask1();
        Future<String> f1 = testAsynTask.doTask2();
        while (true) {
            if (f.isDone() && f1.isDone()) {
                logger.info("Task result: {}", f.get() + f1.get());
                break;
            }
        }
    }
}