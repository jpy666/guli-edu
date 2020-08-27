package com.bupt.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>Title:ConstantVodUtil</p>
 * <p>Description:初始化之后的动作，交给spring管理必须加上component注解</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/6/3 11:38
 * Version 1.0
 */
@Component
public class ConstantVodUtil implements InitializingBean {
    @Value("${aliyun.oss.keyid}")
    private String keyid;

    @Value("${aliyun.oss.keysecret}")
    private String keysecret;

    public static String KEYID;

    public static String KEYSECRET;
    @Override
    public void afterPropertiesSet() throws Exception {
        KEYID = keyid;
        KEYSECRET = keysecret;
    }
}
