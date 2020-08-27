package com.bupt.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.bupt.service.VodService;
import com.bupt.utils.ConstantVodUtil;
import com.bupt.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Title:VodServiceImpl</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/6/3 11:32
 * Version 1.0
 */
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            String title = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
            String fileName = file.getOriginalFilename();
            System.out.println(fileName + "-------");
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtil.KEYID, ConstantVodUtil.KEYSECRET, title, fileName, file.getInputStream());
            System.out.println(ConstantVodUtil.KEYID + "++++++");
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            if (response.isSuccess()) {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
            return response.getVideoId();
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteBatch(List<String> videoIds) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtil.KEYID, ConstantVodUtil.KEYSECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            DeleteVideoResponse response = new DeleteVideoResponse();
            String videoResult = StringUtils.join(Arrays.asList(videoIds),",");
            System.out.println(videoResult + "=============");
            request.setVideoIds(videoResult);
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
