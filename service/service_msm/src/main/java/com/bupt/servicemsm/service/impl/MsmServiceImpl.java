package com.bupt.servicemsm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.bupt.servicemsm.service.MsmService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>Title:MsmServiceImpl</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/7/9 12:11
 * Version 1.0
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Override
    public boolean send(Map<String, Object> param, String phone) {
        //调用短信发送SDK，创建client对象
        DefaultProfile profile = DefaultProfile.getProfile(
                "default",
                "LTAI4GAoU4ZP5BTBtQzCbZ5T",
                "2LyjOgqEnOCrIKcAb8RykUsf0lRQV6");
        IAcsClient client = new DefaultAcsClient(profile);
        //组装请求参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "我的项目在线教育网站");
        request.putQueryParameter("TemplateCode", "SMS_195586944");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            if(success) {
                return true;
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
