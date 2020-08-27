package com.bupt.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bupt.servicebase.exception.GuliException;
import com.bupt.eduservice.entity.EduSubject;
import com.bupt.eduservice.entity.excel.ExcelSubject;
import com.bupt.eduservice.service.EduSubjectService;

/**
 * <p>Title:ExcelSubjectListener</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/30 22:16
 * Version 1.0
 */
public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubject> {
    //这个类需要通过new来创建没必要交给spring来管理  所以需要人为注入
    private EduSubjectService eduSubjectService;
    public ExcelSubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }
    public ExcelSubjectListener() {
    }
    @Override
    public void invoke(ExcelSubject excelSubject, AnalysisContext analysisContext) {
        //有可能没有数据
        if(excelSubject == null) {
            throw new GuliException(20001,"文件数据为空");
        }
        String oneSubject = excelSubject.getOneSubject();
        String twoSubject = excelSubject.getTwoSubject();
        //保存到数据库，前提是判重操作
        EduSubject oneEduSubject = existOneSubject(eduSubjectService,oneSubject);
        if(oneEduSubject == null) {
            EduSubject eduSubject = new EduSubject();
            eduSubject.setTitle(oneSubject);
            eduSubject.setParentId("0");
            eduSubjectService.save(eduSubject);
        }
        String pid = existOneSubject(eduSubjectService,oneSubject).getId();

        EduSubject twoEduSubject = existTowSubject(eduSubjectService,twoSubject,pid);
        if(twoEduSubject == null) {
            EduSubject eduSubject = new EduSubject();
            eduSubject.setTitle(twoSubject);
            eduSubject.setParentId(pid);
            eduSubjectService.save(eduSubject);
        }
    }

    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String oneSubject) {
        QueryWrapper<EduSubject>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",oneSubject);
        queryWrapper.eq("parent_id","0");
        EduSubject eduSubject = eduSubjectService.getOne(queryWrapper);
        if(eduSubject != null) {
            return eduSubject;
        }else {
            return null;
        }
    }

    private EduSubject existTowSubject(EduSubjectService eduSubjectService,String twoSubject,String pid) {
        QueryWrapper<EduSubject>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",twoSubject);
        queryWrapper.eq("parent_id",pid);
        EduSubject eduSubject = eduSubjectService.getOne(queryWrapper);
        if(eduSubject != null) {
            return eduSubject;
        }else {
            return null;
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
