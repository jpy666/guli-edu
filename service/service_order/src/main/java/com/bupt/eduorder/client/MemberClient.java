package com.bupt.eduorder.client;

import com.bupt.commonutils.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface MemberClient {
    @GetMapping("/eduucenter/ucenter/getMemberInfoById/{memberId}")
    public UcenterMemberVo getMemberInfoById(@PathVariable("memberId") String memberId);
}
