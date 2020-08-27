package com.bupt.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.eduservice.entity.course.CoursePublish;
import com.bupt.eduservice.entity.EduCourse;
import com.bupt.eduservice.entity.vo.front.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-05-31
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublish getCoursePublish(String courseId);

    CourseWebVo getCourseInfo(String courseId);
}
