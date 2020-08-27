package com.bupt.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bupt.commonutils.result.R;
import com.bupt.commonutils.util.JWTUtils;
import com.bupt.eduservice.entity.EduCourse;
import com.bupt.eduservice.entity.chapter.ChapterVo;
import com.bupt.eduservice.entity.vo.front.CourseFrontVo;
import com.bupt.eduservice.entity.vo.front.CourseWebVo;
import com.bupt.eduservice.feign.OrderClient;
import com.bupt.eduservice.service.EduChapterService;
import com.bupt.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:CourseFrontController</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/8/4 16:00
 * Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private OrderClient orderClient;

    @PostMapping("getCourseFrontList/{page}/{limit}")
    public R getCourseFrontList(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false)CourseFrontVo courseFrontVo) {
        Page<EduCourse> coursePage = new Page<>(page,limit);
        Map<String,Object> map = eduCourseService.getCourseFrontList(coursePage,courseFrontVo);
        return R.ok().data(map);
    }

    @GetMapping("getBaseCourseInfo/{courseId}")
    public R getBaseCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //查出课程基本信息
        CourseWebVo courseWebVo = eduCourseService.getFrontCourseInfo(courseId);

        List<ChapterVo> chapterVoList = eduChapterService.getChapterVideo(courseId);
        String memberId = JWTUtils.getMemberIdByJwtToken(request);
        if(memberId!=null) {
            boolean buy = orderClient.isBuy(courseId, memberId);
            return R.ok().data("courseWebVo",courseWebVo).data("chapterVoList",chapterVoList).data("isBuy",buy);
        }else {
            return R.ok().data("courseWebVo",courseWebVo).data("chapterVoList",chapterVoList);
        }
    }
}
