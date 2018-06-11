package com.boot.proofing.mpweb.entity;/**
 * @description
 * @autor xbwu on 2017/8/12.
 */


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.mapper.SqlCondition;
import com.boot.core.entity.BaseMpEntity;
import com.boot.core.entity.BaseMpVersionEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

/**
 * 用户表
 *
 * @author xbwu
 * @create 2017-08-12
 **/
@TableName("t_user")
public class User extends BaseMpVersionEntity<User> {

    private String account;

    /**
     * 名称 , condition 属性设置注入
     * 等效于 SQL 为：WHERE name LIKE CONCAT('%',s值,'%')
     */
    @TableField(condition = SqlCondition.LIKE)
    private String username;

    @Column(nullable = false)
    private String password;

    //用户信息
    @TableField(value = "userInfo_id")
    private String userInfoId;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }
}
