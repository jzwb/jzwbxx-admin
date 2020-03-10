package com.jzwbxx.exception;

import com.jzwbxx.common.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Message handle(Exception e) {
        e.printStackTrace();
        if (e instanceof ServiceException) {
            return Message.error("服务异常");
        } else {
            return Message.error("系统异常");
        }
    }
}