package com.bupt.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Title:QueryVo</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/27 8:54
 * Version 1.0
 */
@Data
public class QueryVo {
    @ApiModelProperty(value = "讲师名称  模糊查询")
    private String name;

    @ApiModelProperty(value = "讲师级别")
    private Integer level;

    @ApiModelProperty(value = "讲师注册开始的时间",example = "2019-01-01 00:00:00")
    private String begin;

    @ApiModelProperty(value = "讲师注册结束的时间",example = "2019-01-01 00:00:00")
    private String end;
}
