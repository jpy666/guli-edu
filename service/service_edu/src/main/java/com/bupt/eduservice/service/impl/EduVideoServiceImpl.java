package com.bupt.eduservice.service.impl;

import com.bupt.eduservice.entity.EduVideo;
import com.bupt.eduservice.mapper.EduVideoMapper;
import com.bupt.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-05-31
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public void insertVideo(EduVideo eduVideo) {
        baseMapper.insertEduVideo(eduVideo);
    }
}
