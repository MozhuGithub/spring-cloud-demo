package com.kn.user.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.NoSuchElementException;

/**
 * @ClassName: CheckAdvice
 * @Description TODO: 参数校验异常处理切面
 * @Date: 2019/11/4 18:46
 * @Author: Kn
 */
@ControllerAdvice
public class ExceptionAdvice {
    /**
     * @Description TODO: 捕获前端参数校验异常，并返回对应字段异常信息（可自定义）和Http状态码 400 BAD_REQUEST
     * @Param: [exception]
     * @Return: ResponseEntity<String>
     * @Author: Kn
     * @Date: 2019/11/4 23:00
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleBindException(WebExchangeBindException exception) {
        String errorMessages = exception.getFieldErrors()
                .stream()
                .map(e -> e.getField() + ":" + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    /**
     * @Description TODO: 捕获后端运行时异常，如果是NoSuchElementException 则表示404
     *              TODO: 并返回对应的异常信息（可自定义）和Http状态码 500 INTERNAL_SERVER_ERROR
     * @Param: [runtimeException]
     * @Return: org.springframework.http.ResponseEntity<java.lang.String>
     * @Author: Kn
     * @Date: 2019/11/6 14:20
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException runtimeException) {
        if (runtimeException instanceof NoSuchElementException) {
            return ResponseEntity.status(404).body("没有找到对应资源");
        } else {
            runtimeException.printStackTrace();
            return new ResponseEntity<>(
                    "错误类型：" + runtimeException.getClass().getSimpleName() +
                            "\n错误信息：" + runtimeException.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
