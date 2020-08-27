package com.bupt.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bupt.educms.entity.CmsAd;
import com.bupt.educms.mapper.CmsAdMapper;
import com.bupt.educms.service.CmsAdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * @Cacheable对方法返回的值进行存储
 * </p>
 *
 * @author 金培源
 * @since 2020-07-08
 */
@Service
public class CmsAdServiceImpl extends ServiceImpl<CmsAdMapper, CmsAd> implements CmsAdService {

    @Override
    @Cacheable(value = "banner",key = "'getindexbanner'")
    public List<CmsAd> getAllBanners() {
        QueryWrapper<CmsAd> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 2");
        return baseMapper.selectList(queryWrapper);
    }
}
