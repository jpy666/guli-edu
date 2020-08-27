package com.bupt.serviceucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.commonutils.util.JWTUtils;
import com.bupt.commonutils.util.MD5;
import com.bupt.servicebase.exception.GuliException;
import com.bupt.serviceucenter.entity.UcenterMember;
import com.bupt.serviceucenter.entity.vo.RegisterVo;
import com.bupt.serviceucenter.mapper.UcenterMemberMapper;
import com.bupt.serviceucenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 金培源
 * @since 2020-07-10
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public UcenterMember getWxUcenterMember(String openId) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openId);
        UcenterMember ucenterMember = baseMapper.selectOne(queryWrapper);
        return ucenterMember;
    }

    @Override
    public void regist(RegisterVo registerVo) {
        if(StringUtils.isEmpty(registerVo.getPassword()) ||StringUtils.isEmpty(registerVo.getCode())
                || StringUtils.isEmpty(registerVo.getMobile()) || StringUtils.isEmpty(registerVo.getNickname())) {
            throw new GuliException(20001,"输入的信息为空");
        }
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",registerVo.getMobile());
        int count = this.count(queryWrapper);
        if(count > 0) {
            throw new GuliException(20001,"用户已被注册");
        }
        String code = redisTemplate.opsForValue().get(registerVo.getMobile());
        if(!registerVo.getCode().equals(code)) {
            throw new GuliException(20001,"验证码错误");
        }
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(registerVo.getMobile());
        ucenterMember.setPassword(MD5.encrypt(registerVo.getPassword()));
        ucenterMember.setNickname(registerVo.getNickname());
        ucenterMember.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594356200366&di=4dea4fd32369ed5d5bf7c977aa602663&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fc04c193ee865b8da5f73158970caf43f844bf383268c80-cocj26_fw658");
        this.save(ucenterMember);
    }

    @Override
    public String login(UcenterMember ucenterMember) {
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001,"手机号或者密码为空");
        }
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember member = this.getOne(queryWrapper);
        if(member == null) {
            throw new GuliException(20001,"用户不存在");
        }
        if(!MD5.encrypt(password).equals(member.getPassword())) {
            throw new GuliException(20001,"密码错误");
        }
        if(member.getIsDisabled()) {
            throw new GuliException(20001,"用户已被禁用");
        }
        String token = JWTUtils.generateJwt(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public int countResgister(String day) {
        return baseMapper.countResgister(day);
    }
}
