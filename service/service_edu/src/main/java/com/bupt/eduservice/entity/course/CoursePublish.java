package com.bupt.eduservice.entity.course;

import lombok.Data;

/**
 * <p>Title:CoursePublish</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/6/2 12:28
 * Version 1.0
 */
@Data
public class CoursePublish {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    private String price;//只用于显示
}
