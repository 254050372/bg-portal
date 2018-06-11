package com.boot.proofing.jpaweb.entity;/**
 * @description
 * @autor xbwu on 2017/8/12.
 */



import com.boot.core.entity.BaseEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用户表
 *
 * @author xbwu
 * @create 2017-08-12
 **/

//--------------JSON格式化实体配置-------------
//忽略序列化字段,或者包含哪些序列化字段,可以为空(可选)
//@JSONType(ignores = {"id"}, includes = {"account"})

//--------------xml格式化实体配置--------------
//xml格式化所需根注解(必须)
@XmlRootElement(name = "t_user")
//设置xml根据字段还是方法生成(必须)
@XmlAccessorType(XmlAccessType.FIELD)

//JPA固定标签
@Entity
//指定表名
@Table(name = "t_user",
        //添加索引
        indexes = {
                //索引名称，索引对应列名
                @Index(name="t_user_account", columnList = "account")
                //@Index(name="multi_column_index", columnList = {"col1", "col2"},
        })
public class User extends BaseEntity{

    //是否序列化，格式化等,覆盖JSONType，不写则正常序列化
    //@JSONField(serialize = false)

    //xml节点配置,可以修改节点名称，默认可以不写，输出时是作为节点出现
    //@XmlElement(name="id")
    //xml节点属性配置,输出时是作为节点属性出现
    //@XmlAttribute(name="id")
    //忽略此属性
    //@XmlTransient
    //@Column这里的作用主要是用于限制是否可为空，字段长度等等。可以不加，依然会映射到数据字段，除非用@Transient,字段就不会跟数据库关联
    @Column(nullable = false)
    private String account;

    @Column
    private String username;

    @Column(nullable = false)
    private String password;

    //1有效，0无效
    @Column(nullable = false)
    private Boolean valid;
    //用户信息
    @ManyToOne
    private UserInfo userInfo;

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

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
