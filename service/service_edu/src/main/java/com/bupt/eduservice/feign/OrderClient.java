package com.bupt.eduservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>Title:OrderClient</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/8/7 12:14
 * Version 1.0
 */
@Component
@FeignClient("service-order")
public interface OrderClient {
    @GetMapping("/eduorder/order/isBuy/{courseId}/{memberId}")
    public boolean isBuy(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
