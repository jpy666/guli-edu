package com.bupt.serviceucenter.controller;

import com.bupt.commonutils.util.JWTUtils;
import com.bupt.servicebase.exception.GuliException;
import com.bupt.serviceucenter.entity.UcenterMember;
import com.bupt.serviceucenter.service.UcenterMemberService;
import com.bupt.serviceucenter.util.ConstantWxUtils;
import com.bupt.serviceucenter.util.HttpClientUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * <p>Title:UcenterWxController</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/8/3 16:21
 * Version 1.0
 */
@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class UcenterWxController {
    @Autowired
    private UcenterMemberService ucenterMemberService;
    @GetMapping("callback")
    public String callback(String code,String state) {
        String accessToken = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        String accessTokenUrl = String.format(accessToken, ConstantWxUtils.APP_ID, ConstantWxUtils.APP_SECRET, code);
        HttpClientUtils httpClientUtils = new HttpClientUtils(accessTokenUrl);
        try {
            httpClientUtils.get();
            String result = httpClientUtils.getContent();
            Gson gson = new Gson();
            HashMap map = gson.fromJson(result, HashMap.class);
            //System.out.println(result);
          //  System.out.println(map);
            String access_token  = (String)map.get("access_token");
            String openId = (String)map.get("openid");
            System.out.println(openId);
            UcenterMember ucenterMember = ucenterMemberService.getWxUcenterMember(openId);
            if(ucenterMember == null) {
                String userInfoUri = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(userInfoUri, access_token, openId);
                HttpClientUtils httpClientUtils1 = new HttpClientUtils(userInfoUrl);
                //发送get请求
                httpClientUtils1.get();
                String userInfo = httpClientUtils1.getContent();
                HashMap hashMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String)hashMap.get("nickname");
                String headimgurl = (String)hashMap.get("headimgurl");
                System.out.println(nickname + "===" + headimgurl);
                UcenterMember member = new UcenterMember();
                member.setNickname(nickname);
                member.setOpenid(openId);
                member.setAvatar(headimgurl);
                ucenterMemberService.save(member);
                ucenterMember = ucenterMemberService.getWxUcenterMember(openId);
            }
            String token = JWTUtils.generateJwt(ucenterMember.getId(), ucenterMember.getNickname());
            return "redirect:http://localhost:3000?token=" + token;
        } catch (Exception e) {
            throw new GuliException(20001,"登录失败");
        }
    }
    @GetMapping("login")
    public String login() {
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        String encodeUrl = "";
        String redirectUri = "";
        try {
            encodeUrl = URLEncoder.encode(ConstantWxUtils.REDIRECT_URI, "utf-8");
            redirectUri = String.format(baseUrl, ConstantWxUtils.APP_ID, encodeUrl, "myguli");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:" + redirectUri;
    }
}
