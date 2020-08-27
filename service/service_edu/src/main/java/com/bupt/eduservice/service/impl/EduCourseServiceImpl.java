package com.bupt.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.eduservice.entity.*;
import com.bupt.eduservice.entity.course.CourseInfo;
import com.bupt.eduservice.entity.course.CoursePublish;
import com.bupt.eduservice.entity.vo.front.CourseFrontVo;
import com.bupt.eduservice.entity.vo.front.CourseWebVo;
import com.bupt.eduservice.feign.VodService;
import com.bupt.eduservice.mapper.EduCourseMapper;
import com.bupt.eduservice.service.EduChapterService;
import com.bupt.eduservice.service.EduCourseDescriptionService;
import com.bupt.eduservice.service.EduCourseService;
import com.bupt.eduservice.service.EduVideoService;
import com.bupt.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-05-31
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodService vodService;


    @Override
    public String addCourseInfo(CourseInfo courseInfo) {
        //两步数据库的操作
        //第一步  保存基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0) {
            throw new GuliException(20001,"插入失败");
        }
        //添加之后可以自动回填到该对象中
        //System.out.println(eduCourse.getId() + "--------");
        //第二步  保存描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfo,eduCourseDescription);
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescriptionService.save(eduCourseDescription);
        //在两者之间建立联系
        return eduCourse.getId();
    }

    @Override
    public CourseInfo getCourseInfo(String courseId) {
        //课程基本信息
        CourseInfo courseInfo = new CourseInfo();
        EduCourse eduCourse = baseMapper.selectById(courseId);
        BeanUtils.copyProperties(eduCourse,courseInfo);
        //描述信息
        EduCourseDescription description = eduCourseDescriptionService.getById(courseId);
        BeanUtils.copyProperties(description,courseInfo);
        return courseInfo;
    }

    @Override
    public String updateCourseInfo(CourseInfo courseInfo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0) {
            throw new GuliException(20001,"修改课程让那个信息失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfo,eduCourseDescription);
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescriptionService.updateById(eduCourseDescription);
        return eduCourse.getId();
    }

    @Override
    public CoursePublish getPublishInfo(String courseId) {
        CoursePublish coursePublish = baseMapper.getCoursePublish(courseId);
        return coursePublish;
    }

    @Override
    public void publishCourse(String courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        baseMapper.updateById(eduCourse);
    }

    @Override
    public void deleteCourse(String courseId) {
        //删除章节
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        eduChapterService.remove(queryWrapper);

        //删除小节
        QueryWrapper<EduVideo> queryVideo = new QueryWrapper<>();
        queryVideo.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(queryVideo);
        List<String> videoIds = new ArrayList();
        for(int i = 0;i < eduVideos.size();i ++) {
           String videoId = eduVideos.get(i).getVideoSourceId();
           videoIds.add(videoId);
        }
        System.out.println(videoIds.size() + "==========");
        vodService.deleteBatch(videoIds);
        eduVideoService.remove(queryVideo);

        //删除描述信息
        eduCourseDescriptionService.removeById(courseId);

        //删除课程
        int result = baseMapper.deleteById(courseId);
        if(result == 0) {
            throw new GuliException(20001,"删除失败");
        }
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())){
            queryWrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
            queryWrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCount())) {
            queryWrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isEmpty(courseFrontVo.getGmtCreate())) {
            queryWrapper.orderByDesc("gmt_create");
        }
        if(!StringUtils.isEmpty(courseFrontVo.getPrice())) {
            queryWrapper.orderByDesc("price");
        }
        baseMapper.selectPage(coursePage, queryWrapper);
        List<EduCourse> records = coursePage.getRecords();
        long total = coursePage.getTotal();
        long pages = coursePage.getPages();
        long size = coursePage.getSize();
        long current = coursePage.getCurrent();
        boolean hasNext = coursePage.hasNext();
        boolean hasPrevious = coursePage.hasPrevious();

        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("size", size);
        map.put("items",records);
        map.put("pages",pages);
        map.put("current",current);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }

    @Override
    public CourseWebVo getFrontCourseInfo(String courseId) {
        return baseMapper.getCourseInfo(courseId);
    }
}
