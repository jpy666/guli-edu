package com.bupt.serviceucenter.controller;

import com.bupt.commonutils.result.R;
import com.bupt.commonutils.util.JWTUtils;
import com.bupt.commonutils.vo.UcenterMemberVo;
import com.bupt.serviceucenter.entity.UcenterMember;
import com.bupt.serviceucenter.entity.vo.RegisterVo;
import com.bupt.serviceucenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 金培源
 * @since 2020-07-10
 */
@RestController
@RequestMapping("/eduucenter/ucenter")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //登录方法
    @PostMapping("login")
    public R login(@RequestBody UcenterMember ucenterMember) {
        System.out.println(ucenterMember.getMobile()+"===========");
        String token = ucenterMemberService.login(ucenterMember);
        System.out.println(token + "======");
        return R.ok().data("token",token);
    }


    @PostMapping("regist")
    public R regist(@RequestBody RegisterVo registerVo) {
        ucenterMemberService.regist(registerVo);
        return R.ok().message("注册成功");
    }

    @GetMapping("getMemberInfo")
    public R getMemeberInfo(HttpServletRequest request) {
        String id = JWTUtils.getMemberIdByJwtToken(request);
        UcenterMember ucenterMember = ucenterMemberService.getById(id);
        System.out.println(ucenterMember + "=======");
        return R.ok().data("userInfo",ucenterMember);
    }

    @GetMapping("getMemberInfoById/{memberId}")
    public UcenterMemberVo getMemberInfoById(@PathVariable String memberId) {
        UcenterMember ucenterMember = ucenterMemberService.getById(memberId);
        UcenterMemberVo ucenterMemberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(ucenterMember,ucenterMemberVo);
        return ucenterMemberVo;
    }

    @GetMapping("countRegister/{day}")
    public R countResgister(@PathVariable String day) {
        int count = ucenterMemberService.countResgister(day);
        return R.ok().data("count",count);
    }
}

