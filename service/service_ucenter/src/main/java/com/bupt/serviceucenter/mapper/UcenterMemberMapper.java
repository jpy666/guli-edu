package com.bupt.serviceucenter.mapper;

import com.bupt.serviceucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author 金培源
 * @since 2020-07-10
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    Integer countResgister(String day);
}
