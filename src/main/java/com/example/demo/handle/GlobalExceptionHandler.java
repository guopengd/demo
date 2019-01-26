package com.example.demo.handle;

import com.example.demo.entity.ErrorResponseEntity;
import com.example.demo.utilty.MyException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * 定义要捕获的异常 可以多个 @ExceptionHandler({})
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(AuthorizationException.class)
    public ErrorResponseEntity AuthorizationExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(response.SC_UNAUTHORIZED);
        String message = "无访问权限";
        return new ErrorResponseEntity(response.SC_UNAUTHORIZED, message);
    }

    /**
     * 捕获自定义异常
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(MyException.class)
    public ErrorResponseEntity MyExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(response.SC_BAD_REQUEST);
        MyException exception = (MyException) e;
        return new ErrorResponseEntity(response.SC_BAD_REQUEST, exception.getMsg());
    }

    /**
     * 捕获  RuntimeException 异常
     * 如果不想在前端返回500错误，可将response的status设置为400，并在前端配置400错误页面
     * TODO  如果你觉得在一个 exceptionHandler 通过  if (e instanceof xxxException) 太麻烦
     * TODO  那么你还可以自己写多个不同的 exceptionHandler 处理不同异常
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseEntity runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
        RuntimeException exception = (RuntimeException) e;
        return new ErrorResponseEntity(response.SC_INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    /**
     * 通用的接口映射异常处理方
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            return new ResponseEntity<>(new ErrorResponseEntity(status.value(), exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()), status);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            logger.error("参数转换失败，方法：" + exception.getParameter().getMethod().getName() + "，参数：" + exception.getName()
                    + ",信息：" + exception.getLocalizedMessage());
            return new ResponseEntity<>(new ErrorResponseEntity(status.value(), "参数转换失败"), status);
        }
        return new ResponseEntity<>(new ErrorResponseEntity(status.value(), "参数转换失败"), status);
    }
}