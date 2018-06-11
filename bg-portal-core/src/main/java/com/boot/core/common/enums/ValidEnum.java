package com.boot.core.common.enums;/**
 * @description
 * @autor xbwu on 2017/9/27.
 */

/**
 * 是否有效
 * @author xbwu
 * @create 2017-09-27 
 **/
public enum ValidEnum  {

    VALID(1,"有效"),INVALID(0,"无效");

    private int code;
    private String name;

    ValidEnum(int code,String name){
        this.code=code;
        this.name=name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
