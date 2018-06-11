package com.boot.proofing.common.exceptions;/**
 * @description
 * @autor xbwu on 2017/8/18.
 */

/**
 * 通用ajax请求时业务逻辑错误
 *
 * @author xbwu
 * @create 2017-08-18
 **/
public class AJAXServerException extends RuntimeException {
    public AJAXServerException(String message) {
        super(message);
    }

    public AJAXServerException(String message, Throwable cause) {
        super(message, cause);
    }

}
