package com.bupt.eduservice.entity.custome;

import com.bupt.eduservice.entity.EduCourse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Title:CustomeCourse</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/6/2 17:30
 * Version 1.0
 */
@Data
public class CustomeCourse {
    @ApiModelProperty(value = "课程基本信息")
    private EduCourse eduCourse;

    @ApiModelProperty(value = "课程描述信息")
    private String description;
}
