package com.bupt.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.eduservice.entity.EduTeacher;
import com.bupt.eduservice.mapper.EduTeacherMapper;
import com.bupt.eduservice.service.EduTeacherService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-05-26
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> teacherPage) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        baseMapper.selectPage(teacherPage,queryWrapper);
        List<EduTeacher> records = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        long pages = teacherPage.getPages();
        long size = teacherPage.getSize();
        long current = teacherPage.getCurrent();
        boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();

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
}
