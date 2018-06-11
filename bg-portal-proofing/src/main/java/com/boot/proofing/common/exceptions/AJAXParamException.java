package com.boot.proofing.common.exceptions;/**
 * @description
 * @autor xbwu on 2017/8/18.
 */

/**
 * 通用ajax参数异常切面返回
 * @author xbwu
 * @create 2017-08-18
 **/
public class AJAXParamException extends RuntimeException {
    public AJAXParamException(String message) {
        super(message);
    }
    public AJAXParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
