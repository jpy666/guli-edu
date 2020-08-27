package com.bupt.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bupt.commonutils.result.R;
import com.bupt.commonutils.vo.CourseWeb;
import com.bupt.eduservice.entity.EduCourse;
import com.bupt.eduservice.entity.course.CourseInfo;
import com.bupt.eduservice.entity.course.CoursePublish;
import com.bupt.eduservice.entity.vo.CourseQueryVo;
import com.bupt.eduservice.entity.vo.front.CourseWebVo;
import com.bupt.eduservice.service.EduCourseDescriptionService;
import com.bupt.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-05-31
 */
@RestController
@RequestMapping("/eduservice/educourse")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @PostMapping("addCourse")
    public R addCourseInfo(@RequestBody CourseInfo courseInfo) {
        String courseId = eduCourseService.addCourseInfo(courseInfo);
        return R.ok().data("courseId",courseId);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfo courseInfo = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfo",courseInfo);
    }

    @PostMapping("updateCourse")
    public R updateCourse(@RequestBody CourseInfo courseInfo) {
        String courseId = eduCourseService.updateCourseInfo(courseInfo);
        return R.ok().data("courseId",courseId);
    }

    @GetMapping("getPublishInfo/{courseId}")
    public R getPublishInfo(@PathVariable String courseId) {
        CoursePublish coursePublish = eduCourseService.getPublishInfo(courseId);
        return R.ok().data("coursePublish",coursePublish);
    }

    @PostMapping("publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId) {
        eduCourseService.publishCourse(courseId);
        return R.ok();
    }

    @GetMapping("getAllCourse")
    public R getAllCourse() {
        List<EduCourse> courseList = eduCourseService.list(null);
        return R.ok().data("courseList",courseList);
    }

    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) CourseQueryVo courseQueryVo) {
        Page<EduCourse> page = new Page<>(current,limit);

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(courseQueryVo.getTitle())) {
            queryWrapper.eq("title",courseQueryVo.getTitle());
        }
        if(!StringUtils.isEmpty(courseQueryVo.getStatus())) {
            queryWrapper.eq("status",courseQueryVo.getStatus());
        }
        eduCourseService.page(page,queryWrapper);

        long total = page.getTotal();
        List<EduCourse> records = page.getRecords();
       /* List<CustomeCourse> courses = new ArrayList<>();
        for (EduCourse record : records) {
            CustomeCourse customeCourse = new CustomeCourse();
            EduCourseDescription courseDescription = eduCourseDescriptionService.getById(record.getId());
            customeCourse.setEduCourse(record);
            customeCourse.setDescription(courseDescription.getDescription());
            courses.add(customeCourse);
        }*/
        return R.ok().data("records",records).data("total",total);
    }

    /**
     * 删除章节删除视频
     * @param courseId   课程的id
     * @return
     */
    @DeleteMapping("deleteCourse/{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        eduCourseService.deleteCourse(courseId);
        return R.ok();
    }

    @GetMapping("getCourseInfoById/{courseId}")
    public CourseWeb getCourseInfoById(@PathVariable String courseId) {
        CourseWebVo courseWebVo = eduCourseService.getFrontCourseInfo(courseId);
        CourseWeb courseWeb = new CourseWeb();
        BeanUtils.copyProperties(courseWebVo,courseWeb);
        return courseWeb;
    }
}

