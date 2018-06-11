package com.boot.core.common.base;/**
 * @description
 * @autor xbwu on 2017/8/17.
 */


import com.boot.core.common.enums.ResultEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * ajax返回结果封装
 * @author xbwu
 * @create 2017-08-17 
 **/
public class ResultWapper {

    private String msg;

    private Map<String,Object> result=new HashMap<>();

    private ResultEnum resultType;


    public static ResultWapper success(Map<String,Object> result){
        ResultWapper rw=new ResultWapper();
        rw.setMsg("");
        rw.setResult(result);
        rw.setResultType(ResultEnum.SUCCESS);
        return rw;
    }
    public static ResultWapper success(String msg){
        ResultWapper rw=new ResultWapper();
        rw.setMsg(msg);
        rw.setResultType(ResultEnum.SUCCESS);
        return rw;
    }
    public static ResultWapper success(String msg, Map<String,Object> result){
        ResultWapper rw=new ResultWapper();
        rw.setMsg(msg);
        rw.setResult(result);
        rw.setResultType(ResultEnum.SUCCESS);
        return rw;
    }
    public static ResultWapper error(String msg){
        ResultWapper rw=new ResultWapper();
        rw.setMsg(msg);
        rw.setResultType(ResultEnum.ERROR);
        return rw;
    }
    public static ResultWapper warn(String msg, Map<String,Object> result){
        ResultWapper rw=new ResultWapper();
        rw.setMsg(msg);
        rw.setResult(result);
        rw.setResultType(ResultEnum.WARN);
        return rw;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    private void setResult(Map<String,Object> result) {
        this.result = result;
    }

    public ResultEnum getResultType() {
        return resultType;
    }

    public void setResultType(ResultEnum resultType) {
        this.resultType = resultType;
    }

    public ResultWapper addResult(String key, Object value){
        result.put(key,value);
        return this;
    }
}
