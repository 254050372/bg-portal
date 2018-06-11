package com.boot.core.common.enums;/**
 * @description
 * @autor xbwu on 2017/8/17.
 */

/**
 * 返回code
 * @author xbwu
 * @create 2017-08-17 
 **/
public enum ResultEnum {
    SUCCESS(2000),ERROR(9000),WARN(5000);

    private int code;
    ResultEnum(int code){
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
