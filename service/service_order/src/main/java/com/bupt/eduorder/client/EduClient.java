package com.bupt.eduorder.client;

import com.bupt.commonutils.vo.CourseWeb;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>Title:EduClient</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/8/6 15:25
 * Version 1.0
 */
@FeignClient(name = "service-edu")
@Component
public interface EduClient {
    @GetMapping("/eduservice/educourse/getCourseInfoById/{courseId}")
    public CourseWeb getCourseInfoById(@PathVariable("courseId") String courseId);
}
