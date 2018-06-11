package com.boot.proofing.controller;/**
 * @description
 * @autor xbwu on 2017/11/28.
 */

import com.baomidou.mybatisplus.mapper.Condition;
import com.boot.proofing.common.config.WebSecurityConfig;
import com.boot.proofing.controller.base.BaseController;
import com.boot.proofing.mpweb.entity.User;
import com.boot.proofing.mpweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 首页
 * @author xbwu
 * @create 2017-11-28 
 **/
@RestController
public class IndexController extends BaseController {

    @Autowired
    IUserService userService;

    @RequestMapping("/")
    public ModelAndView login(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        if (session.getAttribute(WebSecurityConfig.USER) != null)
            return redirectController("index");
        ModelAndView mv = getMV("login");
        return mv;
    }

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) throws Exception{
        ModelAndView mv = getMV("index");
        return mv;
    }

    @RequestMapping("/homePage")
    public ModelAndView homePage(HttpServletRequest request) throws Exception{
        ModelAndView mv = getMV("homePage");
        return mv;
    }

}
