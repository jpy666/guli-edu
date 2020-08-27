package com.bupt.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bupt.commonutils.result.R;
import com.bupt.eduservice.entity.EduCourse;
import com.bupt.eduservice.entity.EduTeacher;
import com.bupt.eduservice.service.EduCourseService;
import com.bupt.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Title:IndexFront</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/7/8 11:41
 * Version 1.0
 */
@RestController
@RequestMapping("/eduservice/index")
@CrossOrigin
public class IndexFront {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("index")
    public R index() {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 8");
        List<EduCourse> eduCourseList = eduCourseService.list(queryWrapper);

        QueryWrapper<EduTeacher> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        queryWrapper1.last("limit 4");
        List<EduTeacher> eduTeacherList = eduTeacherService.list(queryWrapper1);

        return R.ok().data("courseList",eduCourseList).data("teacherList",eduTeacherList);
    }
}
