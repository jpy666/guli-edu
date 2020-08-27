package com.bupt.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.eduservice.entity.EduChapter;
import com.bupt.eduservice.entity.EduVideo;
import com.bupt.eduservice.entity.chapter.ChapterVo;
import com.bupt.eduservice.mapper.EduChapterMapper;
import com.bupt.eduservice.service.EduChapterService;
import com.bupt.eduservice.service.EduVideoService;
import com.bupt.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-05-31
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        //根据courseId查询所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> chapters = baseMapper.selectList(wrapperChapter);
        //根据courseId查询所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> videos = eduVideoService.list(wrapperVideo);
        //循环遍历添加进chapterVo中
        List<ChapterVo> chapterVoList = new ArrayList<>();
        for (EduChapter eduChapter : chapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            //List<VideoVo> videoVoList = new ArrayList<>();
            List<EduVideo> videoList =  new ArrayList<>();
            for (EduVideo eduVideo : videos) {
                if(eduVideo.getChapterId().equals(eduChapter.getId())) {
                    //VideoVo videoVo = new VideoVo();
                    //BeanUtils.copyProperties(eduVideo,videoVo);
                    //videoVoList.add(videoVo);
                    videoList.add(eduVideo);
                }
            }
            chapterVo.setChildren(videoList);
            chapterVoList.add(chapterVo);
        }
        return chapterVoList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(queryWrapper);
        if(count > 0) {
            throw new GuliException(20001,"小节不为空");
        }
        int result = baseMapper.deleteById(chapterId);
        return result > 0;
    }
}
