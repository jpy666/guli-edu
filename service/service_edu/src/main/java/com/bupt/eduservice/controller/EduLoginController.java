package com.bupt.eduservice.controller;

import com.bupt.commonutils.result.R;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Title:EduLoginController</p>
 * <p>Description:  协议  ip地址  端口号有一个不一致会出现跨域问题</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/29 8:41
 * Version 1.0
 */
@RestController
@RequestMapping("/eduservice/eduadmin")
@CrossOrigin   //解决跨域问题
public class EduLoginController {
    @PostMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wimg.ruan8.com/uploadimg/image/20191008/20191008174421_69357.jpg");
    }
}
