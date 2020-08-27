package com.bupt.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.bupt.commonutils.result.R;
import com.bupt.service.VodService;
import com.bupt.servicebase.exception.GuliException;
import com.bupt.utils.ConstantVodUtil;
import com.bupt.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>Title:VodController</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/6/3 11:31
 * Version 1.0
 */
@RestController
@RequestMapping("/eduvod/vod")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;

    @PostMapping("uploadVideo")
    public R uploadVideo(MultipartFile file) {
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }

    @DeleteMapping("deleteAliyunVideo/{videoId}")
    public R deleteAliyunVideo(@PathVariable String videoId) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtil.KEYID, ConstantVodUtil.KEYSECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            DeleteVideoResponse response = new DeleteVideoResponse();
            request.setVideoIds(videoId);
            response = client.getAcsResponse(request);
            return R.ok();
        } catch (ClientException e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam(name = "videoIds") List<String> videoIds) {
        vodService.deleteBatch(videoIds);
        return R.ok();
    }

    @GetMapping("getPlayAuth/{videoId}")
    public R getPlayAuth(@PathVariable String videoId) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtil.KEYID, ConstantVodUtil.KEYSECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        } catch (ClientException e) {
           throw new GuliException(20001,"获取凭证失败");
        }
    }
}
