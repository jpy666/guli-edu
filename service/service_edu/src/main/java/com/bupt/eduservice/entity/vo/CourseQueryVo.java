package com.bupt.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Title:CourseQueryVo</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/6/2 16:58
 * Version 1.0
 */
@Data
public class CourseQueryVo {
    @ApiModelProperty(name = "title",value = "课程名称")
    private String title;

    @ApiModelProperty(name = "status",value = "是否发布")
    private String status;
}
