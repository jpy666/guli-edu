package com.bupt.eduservice.mapper;

import com.bupt.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-05-31
 */
public interface EduVideoMapper extends BaseMapper<EduVideo> {
    void insertEduVideo(EduVideo eduVideo);
}
