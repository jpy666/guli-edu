package com.bupt.oss.controller;

import com.bupt.commonutils.result.R;
import com.bupt.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Title:OssController</p>
 * <p>Description:后台代码都使用swagger测试</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/30 9:45
 * Version 1.0
 */
@RestController
@RequestMapping("/eduoss/oss")
@CrossOrigin
@Api(description = "对象存储服务")
public class OssController {
    @Autowired
    private OssService ossService;
    @PostMapping("uploadFile")
    @ApiOperation(value = "上传文件到OSS")
    public R uploadFile(MultipartFile file) {
        String url = ossService.uploadOssFile(file);
        return R.ok().data("url",url);
    }
}
