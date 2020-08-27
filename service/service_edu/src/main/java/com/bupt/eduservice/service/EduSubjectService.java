package com.bupt.eduservice.service;

import com.bupt.eduservice.entity.EduSubject;
import com.bupt.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-05-30
 */
public interface EduSubjectService extends IService<EduSubject> {
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllSubjects();
}
