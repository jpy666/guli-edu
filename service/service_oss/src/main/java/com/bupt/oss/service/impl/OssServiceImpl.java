package com.bupt.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.bupt.oss.service.OssService;
import com.bupt.oss.utils.OssConstantUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * <p>Title:OssServiceImpl</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/30 9:12
 * Version 1.0
 */
@Service
public class OssServiceImpl implements OssService {

    /**
     * 返回url的地址(指的是oss中的地址)
     * @param file
     * @return
     */
    @Override
    public String uploadOssFile(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = OssConstantUtil.ENDPOINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = OssConstantUtil.KEYID;
        String accessKeySecret = OssConstantUtil.KEYSECRET;
        String bucketName = OssConstantUtil.BUCKETNAME;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //得到文件名  同名文件会被覆盖  为了解决需要生成唯一的文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //上传到指定路径  识别出文件夹
        String date = new DateTime().toString("yyyy/MM/dd");
        String fileName = date + "/" + uuid + "-" + file.getOriginalFilename();

        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            //第二个参数指的是oss中的文件的存储路径和文件名
            ossClient.putObject(bucketName,fileName, inputStream);
            //https://guli-edut.oss-cn-shanghai.aliyuncs.com/a.jpg
            String url ="https://" + bucketName + "." + endpoint + "/" + fileName;
            // 关闭OSSClient。
            ossClient.shutdown();
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
