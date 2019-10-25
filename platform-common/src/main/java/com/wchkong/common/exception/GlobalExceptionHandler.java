package com.wchkong.common.exception;

import com.wchkong.common.ServerResponse;
import com.wchkong.common.enums.ServerResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常拦截类
 *
 * @Author: kongweichang
 * @Date: 2019/6/5 17:39
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = ServerException.class)
    public ServerResponse serverExceptionHandler(HttpServletRequest request, Exception e){
        ServerException serverException = (ServerException) e;
        log.error("GlobalExceptionHandler::serverExceptionHandler e=", e);
        return ServerResponse.error(serverException);

    }

    @ExceptionHandler(value = Exception.class)
    public ServerResponse exceptionHandler(HttpServletRequest request, Exception e){
        log.error("GlobalExceptionHandler::exceptionHandler e=", e);
        return ServerResponse.error(ServerResponseEnum.SERVER_ERROR);
    }
}
