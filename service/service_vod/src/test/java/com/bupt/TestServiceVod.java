package com.bupt;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadFileStreamRequest;
import com.aliyun.vod.upload.resp.UploadFileStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.bupt.utils.InitVodClient;

import java.util.List;

/**
 * <p>Title:TestServiceVod</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/6/3 9:23
 * Version 1.0
 */
public class TestServiceVod {
    private static String accessKeyId = "LTAI4GAoU4ZP5BTBtQzCbZ5T";
    private static String accessKeySecret = "2LyjOgqEnOCrIKcAb8RykUsf0lRQV6";
    public static void main(String[] args) throws Exception {
        //getVideoAuth();
        String title = "test.mp4";
        String fileName = "G:\\ssm复习\\01教学资料\\尚硅谷-全栈在线教育项目-谷粒学院【Vue.js+Spring Cloud Alibaba】\\day11-大纲管理和阿里云VOD\\资料\\video\\2.mp4";
        UploadFileStreamRequest request = new UploadFileStreamRequest(accessKeyId, accessKeySecret, title, fileName);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadFileStreamResponse response = uploader.uploadFileStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n"); //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
    public static void getVideoUrl() throws Exception{
        DefaultAcsClient defaultAcsClient = InitVodClient.initVodClient(accessKeyId, accessKeySecret);
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        request.setVideoId("56d7c8751a214e28aefb96655c626c4a");
        response = defaultAcsClient.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
    public static void getVideoAuth() throws Exception{
        DefaultAcsClient defaultAcsClient = InitVodClient.initVodClient(accessKeyId,accessKeySecret);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId("56d7c8751a214e28aefb96655c626c4a");
        response = defaultAcsClient.getAcsResponse(request);
        response.getPlayAuth();
        System.out.println(response.getPlayAuth());
    }
}
