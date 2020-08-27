package com.bupt.serviceucenter.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>Title:ConstantWxUtils</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/8/3 16:25
 * Version 1.0
 */
@Component
public class ConstantWxUtils implements InitializingBean {
    @Value("${wx.open.appId}")
    private String appId;
    @Value("${wx.open.appSecret}")
    private String appSecret;
    @Value("${wx.open.redirectUri}")
    private String redirectUri;

    public static String APP_ID;
    public static String APP_SECRET;
    public static String REDIRECT_URI;
    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID=appId;
        APP_SECRET=appSecret;
        REDIRECT_URI=redirectUri;
    }
}
