package com.boot.proofing.common.advice;/**
 * @description
 * @autor xbwu on 2017/8/18.
 */

import com.boot.core.common.base.ResultWapper;
import com.boot.proofing.common.config.BaseConstant;
import com.boot.proofing.common.exceptions.AJAXServerException;
import com.boot.proofing.common.exceptions.AJAXParamException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 异常捕获切面
 * @author xbwu
 * @create 2017-08-18 
 **/
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * exception最上级异常捕获输出，如果程序不捕获则返回500
     */
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e,HttpServletRequest request) {
        String header=request.getHeader("x-requested-with");
        logger.error("服务器异常，请稍后再试",e);
        if(StringUtils.isBlank(header)){//判断是否ajax请求，不是则跳转页面，是则返回标准封装结果
            return new ModelAndView(BaseConstant.templatesPrefix+"/common/error/404");
        }else{
            return ResultWapper.error("服务器异常，请稍后再试");
        }
    }

    /**
     * hibernate validate 验证返回
     * @param e
     * @return
     */
    @ExceptionHandler(value = { ConstraintViolationException.class })
    public Object handleResourceNotFoundException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations ) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        return ResultWapper.error(strBuilder.toString());
    }

    /**
     * 自定义异常捕获,用于ajax请求参数错误返回
     */
    @ExceptionHandler(AJAXParamException.class)
    public ResultWapper handleParamException(AJAXParamException e) {
        return ResultWapper.error(e.getMessage());
    }
    /**
     * 自定义异常捕获,用于ajax请求时业务逻辑错误返回
     */
    @ExceptionHandler(AJAXServerException.class)
    public ResultWapper handleServerException(AJAXParamException e) {
        return ResultWapper.error(e.getMessage());
    }
}
