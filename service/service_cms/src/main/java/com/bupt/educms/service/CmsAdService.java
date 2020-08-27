package com.bupt.educms.service;

import com.bupt.educms.entity.CmsAd;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务类
 * </p>
 *
 * @author 金培源
 * @since 2020-07-08
 */
public interface CmsAdService extends IService<CmsAd> {

    List<CmsAd> getAllBanners();
}
