package com.boot.core.entity;/**
 * @description
 * @autor xbwu on 2018/5/14.
 */

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * mybatis-plus基类实体
 * 增加乐观锁机制字段
 * @author xbwu
 * @create 2018-05-14 
 **/
public class BaseMpVersionEntity<T extends Model> extends BaseMpEntity<T> {

    /**
     * mp乐观锁注解
     */
    @Version
    protected Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
