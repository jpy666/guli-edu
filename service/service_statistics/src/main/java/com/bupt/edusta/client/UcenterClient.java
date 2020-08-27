package com.bupt.edusta.client;

import com.bupt.commonutils.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>Title:UcenterClient</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/8/7 18:48
 * Version 1.0
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping("/eduucenter/ucenter/countRegister/{day}")
    public R countResgister(@PathVariable("day") String day);
}
