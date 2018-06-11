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
 * @author xbwu
 * @create 2018-05-14 
 **/
public class BaseMpEntity<T extends Model> extends Model<T> {
    /**
     * 主键ID
     */
    @TableId("id")
    protected Long id;

    protected Long tenantId;

    /**
     * 逻辑删除字段，默认值在MyMetaObjectHandler填入，逻辑删除具体值配置在application中
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    protected String isDelete;

    //1有效，0无效
    @Column(nullable = false)
    protected Boolean valid;
    /**
     * 修改时间
     * 因为调用更新和插入的方法时，会验证你所传的属性是否为空，来判断是否该更新和插入这个属性
     * 这个就和MP的公共字段自动填充相冲突了，所以需要配置：fill = FieldFill.INSERT 来标识此属性是需要全局注入的。
     * 不然在插入的时候就会填充失败。具体值在MyMetaObjectHandler填入
     */
    @TableField(value = "modify_dateTime",fill = FieldFill.INSERT_UPDATE)
    protected Date modifyDateTime;
    /**
     * 创建时间
     */
    @TableField(value = "create_dateTime",fill = FieldFill.INSERT)
    protected Date createDateTime;
    /**
     * 操作人
     */
    protected Long operator;
    /**
     * mp乐观锁注解
     */
    @Version
    protected Long version;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Date getModifyDateTime() {
        return modifyDateTime;
    }

    public void setModifyDateTime(Date modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }
    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
