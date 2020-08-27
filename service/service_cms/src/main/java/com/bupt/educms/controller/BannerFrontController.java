package com.bupt.educms.controller;

import com.bupt.commonutils.result.R;
import com.bupt.educms.entity.CmsAd;
import com.bupt.educms.service.CmsAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Title:BannerFrontController</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/7/8 10:57
 * Version 1.0
 */
@RestController
@RequestMapping("/educms/bannerFront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CmsAdService cmsAdService;
    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CmsAd> list = cmsAdService.getAllBanners();
        return R.ok().data("list",list);
    }
}
