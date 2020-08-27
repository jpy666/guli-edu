package com.bupt.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Title:OssService</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/30 9:11
 * Version 1.0
 */
public interface OssService {
    public String uploadOssFile(MultipartFile file);
}
