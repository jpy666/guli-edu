package com.bupt.servicemsm.controller;

import com.bupt.commonutils.result.R;
import com.bupt.servicemsm.service.MsmService;
import com.bupt.servicemsm.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:MsmController</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/7/9 12:09
 * Version 1.0
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{phone}")
    public R send(@PathVariable String phone) {
        //设置过期时间使用redis来实现
        String phone1 = redisTemplate.opsForValue().get(phone);
        if(StringUtils.isEmpty(phone1)) {
            //键值对的形式后面 更加方便
            Map<String,Object> param = new HashMap<>();
            String code = RandomUtils.getSixBitRandom();
            param.put("code",code);
            boolean result = msmService.send(param,phone);
            if(result) {
                redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
                return R.ok().message("发送成功");
            }else {
                return R.error().message("短信发送失败");
            }
        }else {
            return R.ok().message("发送成功");
        }
    }
}
