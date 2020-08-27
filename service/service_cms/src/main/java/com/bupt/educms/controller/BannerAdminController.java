package com.bupt.educms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bupt.commonutils.result.R;
import com.bupt.educms.entity.CmsAd;
import com.bupt.educms.service.CmsAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 广告推荐 前端控制器
 * </p>
 *
 * @author 金培源
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {
    @Autowired
    private CmsAdService cmsAdService;
    //分页查询
    @GetMapping("pageBanner/{current}/{limit}")
    public R pageBanner(@PathVariable long current, @PathVariable long limit) {
        Page<CmsAd> pageCmsAd = new Page<>(current,limit);
        cmsAdService.page(pageCmsAd,null);
        return R.ok().data("total",pageCmsAd.getTotal()).data("records",pageCmsAd.getRecords());
    }
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CmsAd cmsAd) {
        cmsAdService.save(cmsAd);
        return R.ok();
    }
    @DeleteMapping("deleteBanner/{id}")
    public R deleteBanner(@PathVariable String id) {
        cmsAdService.removeById(id);
        return R.ok();
    }
    @PostMapping("updateBanner")
    public R updateBanner(@RequestBody CmsAd cmsAd) {
        cmsAdService.updateById(cmsAd);
        return R.ok();
    }
    @GetMapping("getBanner/{id}")
    public R getBanner(@PathVariable String id) {
        CmsAd cmsAd = cmsAdService.getById(id);
        return R.ok().data("cmsAd",cmsAd);
    }
}

