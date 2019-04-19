package com.hxf.mall.ctrl;

import com.hxf.mall.to.ResponseData;
import com.hxf.mall.to.ResultEnums;
import com.hxf.mall.util.ResponseDataUtil;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public ResponseData handle401() {
        return ResponseDataUtil.buildError(ResultEnums.PERMISSION_DENY);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public ResponseData globalException(HttpServletRequest request, Throwable ex) {
        return ResponseDataUtil.buildError(ResultEnums.SYSTEM_ERROR);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
