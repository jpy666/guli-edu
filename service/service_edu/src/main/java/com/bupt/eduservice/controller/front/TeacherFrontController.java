package com.bupt.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bupt.commonutils.result.R;
import com.bupt.eduservice.entity.EduCourse;
import com.bupt.eduservice.entity.EduTeacher;
import com.bupt.eduservice.service.EduCourseService;
import com.bupt.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Title:TeacherFrontController</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/8/4 9:51
 * Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page,@PathVariable long limit) {
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        Map<String,Object> map = eduTeacherService.getTeacherFrontList(teacherPage);
        return R.ok().data(map);
    }

    @GetMapping("getTeacherInfo/{id}")
    public R getTeacherInfo(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",id);
        List<EduCourse> eduCourseList = eduCourseService.list(queryWrapper);
        return R.ok().data("eduTeacher",eduTeacher).data("eduCourseList",eduCourseList);
    }
}
