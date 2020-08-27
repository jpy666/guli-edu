package com.bupt.eduservice.controller;

import com.bupt.eduservice.entity.subject.OneSubject;
import com.bupt.commonutils.result.R;
import com.bupt.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-05-30
 */
@CrossOrigin
@Api(description = "课程分类管理")
@RestController
@RequestMapping("/eduservice/edusubject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("uploadSubject")
    public R uploadSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }

    @GetMapping("getAllSubjects")
    public R getAllSubjects() {
        List<OneSubject> oneSubjectList = eduSubjectService.getAllSubjects();
        return R.ok().data("list",oneSubjectList);
    }
}

