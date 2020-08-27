package com.bupt.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.eduservice.entity.subject.OneSubject;
import com.bupt.eduservice.entity.subject.TwoSubject;
import com.bupt.eduservice.listener.ExcelSubjectListener;
import com.bupt.eduservice.entity.EduSubject;
import com.bupt.eduservice.entity.excel.ExcelSubject;
import com.bupt.eduservice.mapper.EduSubjectMapper;
import com.bupt.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-05-30
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubject.class,new ExcelSubjectListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllSubjects() {
        //查询所有的一级分类
        QueryWrapper<EduSubject> oneQueryWrapper = new QueryWrapper<>();
        oneQueryWrapper.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(oneQueryWrapper);

        //查询所有的二级分类
        QueryWrapper<EduSubject> twoQueryWrapper = new QueryWrapper<>();
        oneQueryWrapper.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(twoQueryWrapper);
        List<OneSubject> finalOneSubjectList = new ArrayList<>();
        for(EduSubject eduSubject : oneSubjectList) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            List<TwoSubject> finalTwoSubjectList = new ArrayList<>();
            for(EduSubject eduSubject1 : twoSubjectList) {
                if(eduSubject1.getParentId().equals(oneSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject1,twoSubject);
                    finalTwoSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(finalTwoSubjectList);
            finalOneSubjectList.add(oneSubject);
        }
        return finalOneSubjectList;
    }
}
