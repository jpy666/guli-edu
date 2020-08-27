package com.bupt.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bupt.eduservice.entity.EduCourse;
import com.bupt.eduservice.entity.course.CourseInfo;
import com.bupt.eduservice.entity.course.CoursePublish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.eduservice.entity.vo.front.CourseFrontVo;
import com.bupt.eduservice.entity.vo.front.CourseWebVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-05-31
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfo courseInfo);

    CourseInfo getCourseInfo(String courseId);

    String updateCourseInfo(CourseInfo courseInfo);

    CoursePublish getPublishInfo(String courseId);

    void publishCourse(String courseId);

    void deleteCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo);

    CourseWebVo getFrontCourseInfo(String courseId);
}
