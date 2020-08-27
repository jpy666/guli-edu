package com.bupt.eduservice.feign;

import com.bupt.commonutils.result.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Title:VodServiceImpl</p>
 * <p>Description:交给spring来管理</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/7/6 10:43
 * Version 1.0
 */
@Component
public class VodServiceImpl implements VodService{
    @Override
    public R deleteAliyunVideo(String videoId) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIds) {
        return R.error().message("删除课程出错了");
    }
}
