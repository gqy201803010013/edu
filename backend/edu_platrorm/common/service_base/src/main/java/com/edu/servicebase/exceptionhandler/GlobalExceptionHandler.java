package com.edu.servicebase.exceptionhandler;

//import com.edu.commonutils.R;
import com.edu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
     public R error(Exception e){
            log.error(e.getMessage());

            e.printStackTrace();

            return R.error().message("执行了异常处理");
    }
}
