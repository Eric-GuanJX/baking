package cn.tedu.boot01.exception;


import cn.tedu.boot01.response.JsonResult;
import cn.tedu.boot01.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * ControllerAdvice: 标识当前类为全局异常处理器类型,
 *    可以捕获和处理Controller中抛出的异常。
 * RestControllerAdvice:复合注解,等价于 ControllerAdvice 和 ResponseBody 注解
 * 当某个Controller中出现了异常,系统底层会查找有没有定义全局异常处理器对象;
 * 以及全局异常处理器对象中有没有定义对应的异常处理方法,如果有则调用此方法处理异常。
 */
//@ControllerAdvice
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ExceptionHandler：标识当前方法为异常处理方法,参数为捕获到的异常的对象
     * @param ex 具体的异常对象
     * ex.getMessage()方法: 获取异常中的提示消息
     */
    @ExceptionHandler
    public JsonResult doHandleRuntimeException(RuntimeException ex){
        log.error("error is: " + ex.getMessage());
        return new JsonResult(StatusCode.OPERATION_FAILED, ex.getMessage());
    }

    /**
     * 当Controller中出现异常时,会优先在全局异常处理器中使用对应异常的处理方法处理异常;
     * 如果没有对应的异常处理方法,则会找该异常的父类异常处理方法处理该异常。
     */
    @ExceptionHandler
    public JsonResult doHandleIllegalArgumentException(IllegalArgumentException ex){
        log.error("IllegalArgumentException error is: " + ex.getMessage());
        return new JsonResult(StatusCode.OPERATION_FAILED, ex.getMessage());
    }

    /**
     * Validation中参数校验失败的异常处理方法
     * @param ex 异常对象
     * @return
     */
    @ExceptionHandler
    public JsonResult doHandleArgumentNotValidException(MethodArgumentNotValidException ex){
        /*
          ex.getFieldError().getDefaultMessage())：获取valitation中的异常消息
         */
        return new JsonResult(StatusCode.VALIDATE_ERROR, ex.getFieldError().getDefaultMessage());
    }


    /**
     * 捕获所有异常并处理
     */
//    @ExceptionHandler
//    public JsonResult doHandleThrowable(Throwable ex){
//        log.error("程序运行中出现Throwable");
//        return new JsonResult(StatusCode.OPERATION_FAILED, "程序运行中出现Throwable");
//    }

    //import org.springframework.security.core.AuthenticationException;
    @ExceptionHandler({InternalAuthenticationServiceException.class,
            BadCredentialsException.class})
    public JsonResult handleAuthenticationException(
            AuthenticationException e){
        if (e instanceof InternalAuthenticationServiceException){
            log.warn("用户名不存在!");
            return new JsonResult(StatusCode.USERNAME_ERROR);
        }
        log.warn("密码错误!");
        return new JsonResult(StatusCode.PASSWORD_ERROR);
    }

}







