package com.boot.proofing.controller;/**
 * @description
 * @autor xbwu on 2018/1/22.
 */

import com.boot.core.common.base.ResultWapper;
import com.boot.core.common.util.LocaleMessageSource;
import com.boot.core.common.util.MD5Util;
import com.boot.proofing.common.config.WebSecurityConfig;
import com.boot.proofing.controller.base.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

import com.boot.proofing.mpweb.entity.User;
import com.boot.proofing.mpweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpSession;

/**
 * 登录
 * @author xbwu
 * @create 2018-01-22 
 **/


@RestController
public class LoginController extends BaseController{


    @Autowired
    @Qualifier("localJdbcTemplate")
    JdbcTemplate jdbcTemplate;
    /**
     * 国际化消息
     */
    @Autowired
    private LocaleMessageSource i18n;

    @Autowired
    IUserService userService;

    @GetMapping(value = "/login")
    public ModelAndView login() throws Exception {
        ModelAndView mv = getMV("login");
        return mv;
    }
    @PostMapping(value = "/login")
    public ResultWapper login(Model model,
                              @NotBlank(message = "用户名不能为空") String username,
                              @NotBlank(message = "密码不能为空") String password,
                              HttpServletRequest request) throws Exception {

        User ue = userService.findByUserAccount(username.trim());
        ResultWapper rw = null;
        do {
            if (ue == null) {
                rw = ResultWapper.error(i18n.getMsg("login.incorrect.username.or.password"));
                break;
            }
            if (!ue.getValid()) {
                rw = ResultWapper.error(i18n.getMsg("login.incorrect.username.disable"));
                break;
            }
            String dbpd = ue.getPassword();
            String nowpd = MD5Util.getMD5(password.trim());
            if (!dbpd.equals(nowpd)) {
                rw = ResultWapper.error(i18n.getMsg("login.incorrect.username.or.password"));
                break;
            }
            rw=ResultWapper.success(i18n.getMsg("login.success"));
            rw.addResult("ue",ue);
            WebUtils.setSessionAttribute(request, WebSecurityConfig.USER,ue);
        } while (false);
        return rw;
    }


    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.USER);
        return redirectController("login");
    }

}
