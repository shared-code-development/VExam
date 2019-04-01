package org.exam.exception;

import lombok.extern.slf4j.Slf4j;
import org.exam.bean.dto.ResponseBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局统一异常处理
 */
@Slf4j
@ControllerAdvice(basePackages = {"org.exam"})
public class GlobalExceptionHandler {
    /**
     * 异常处理
     *
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseBean handException(HttpServletRequest req, Exception e){
        log.error("异常类型：{}", e.getClass().getName());
        log.error("异常信息：{}", e.getMessage());
        log.error("异常地址：{}", e.getStackTrace());
        // service 层业务异常
        if (e instanceof BusinessException) {
            return ResponseBean.error((BusinessException) e);
        }
        return ResponseBean.error(e.getMessage());
    }
}
