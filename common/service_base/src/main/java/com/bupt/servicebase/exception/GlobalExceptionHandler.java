package com.bupt.servicebase.exception;

import com.bupt.commonutils.util.ExceptionUtil;
import com.bupt.commonutils.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title:GlobalExceptionHandler</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/27 10:40
 * Version 1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 定义全局异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.ok().message("出现了异常");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.ok().message("进行了特定异常处理");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e) {
        log.error(ExceptionUtil.getMessage(e));
        return R.ok().code(e.getCode()).message(e.getMsg());
    }
}
