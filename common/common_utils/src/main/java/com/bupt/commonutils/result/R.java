package com.bupt.commonutils.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title:R</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/27 8:06
 * Version 1.0
 */
@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回的消息")
    private String message;

    @ApiModelProperty(value = "返回的数据")
    private Map<String,Object> data = new HashMap<>();

    /**
     * 构造函数私有化，只能调用方法，不能new
     */
    private R() {

    }

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R data(String key,Object value) {
        this.data.put(key,value);
        return this;
    }

    public R data(Map<String,Object> map) {
        this.setData(map);
        return this;
    }
}
