package com.bupt.eduservice.controller;

import com.bupt.commonutils.result.R;
import com.bupt.eduservice.entity.EduVideo;
import com.bupt.eduservice.feign.VodService;
import com.bupt.eduservice.service.EduVideoService;
import com.bupt.servicebase.exception.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/eduservice/eduvideo")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodService vodService;

    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        System.out.println(eduVideo.getVideoSourceId() + "-----------");
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    @GetMapping("getVideoInfo/{videoId}")
    public R getVideoInfo(@PathVariable String videoId) {
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("video",eduVideo);
    }

    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }

    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId) {
        //先删除视频
        EduVideo eduVideo = eduVideoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)) {
            R result = vodService.deleteAliyunVideo(videoSourceId);
            if(result.getCode() == 20001) {
                throw new GuliException(20001,"删除视频失败了，熔断器");
            }
        }
        eduVideoService.removeById(videoId);
        return R.ok();
    }
}

