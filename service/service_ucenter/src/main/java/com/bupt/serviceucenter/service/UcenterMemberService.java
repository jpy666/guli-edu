package com.bupt.serviceucenter.service;

import com.bupt.serviceucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.serviceucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 金培源
 * @since 2020-07-10
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    UcenterMember getWxUcenterMember(String openId);

    void regist(RegisterVo registerVo);

    String login(UcenterMember ucenterMember);

    int countResgister(String day);
}
