package com.bupt.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>Title:OssConstantUtil</p>
 * <p>Description:在spring中可以通过value注解直接获取
 * 配置文件中的参数的值
 * </P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/30 9:15
 * Version 1.0
 */
@Component
public class OssConstantUtil implements InitializingBean {
    @Value("${aliyun.oss.endpoint}")
    private String endPoint;

    @Value("${aliyun.oss.keyid}")
    private String keyId;

    @Value("${aliyun.oss.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.bucketname}")
    private String bucketName;

    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;

    /**
     * 初始化bean之后所做的事
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endPoint;
        KEYID = keyId;
        KEYSECRET = keySecret;
        BUCKETNAME = bucketName;
    }
}
