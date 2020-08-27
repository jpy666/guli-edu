package com.bupt.eduservice.feign;

import com.bupt.commonutils.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod",fallback = VodServiceImpl.class)
@Component
public interface VodService {
    @DeleteMapping("/eduvod/vod/deleteAliyunVideo/{videoId}")   //要使用全路径
    public R deleteAliyunVideo(@PathVariable("videoId") String videoId);
    @DeleteMapping("/eduvod/vod/delete-batch")
    public R deleteBatch(@RequestParam(name = "videoIds") List<String> videoIds);
}
