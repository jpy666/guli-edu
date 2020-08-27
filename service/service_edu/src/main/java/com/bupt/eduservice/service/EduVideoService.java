package com.bupt.eduservice.service;

import com.bupt.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-05-31
 */
public interface EduVideoService extends IService<EduVideo> {

    void insertVideo(EduVideo eduVideo);
}
