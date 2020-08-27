package com.bupt.edusta.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bupt.commonutils.result.R;
import com.bupt.edusta.client.UcenterClient;
import com.bupt.edusta.entity.StatisticsDaily;
import com.bupt.edusta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-08-07
 */
@RestController
@RequestMapping("/edusta/sta")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private UcenterClient ucenterClient;
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @PostMapping("createSta/{day}")
    public R createSta(@PathVariable String day) {
        statisticsDailyService.createSta(day);
        return R.ok();
    }

    @PostMapping("showChart/{type}/{begin}/{end}")
    public R showChart(@PathVariable String type, @PathVariable String begin, @PathVariable String end) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated",begin,end);
        queryWrapper.select("date_calculated",type);
        List<StatisticsDaily> dailyList = statisticsDailyService.list(queryWrapper);

        List<String> xData = new ArrayList<>();
        List<Integer> yData = new ArrayList<>();

        for(int i = 0;i < dailyList.size();i ++) {
             xData.add(dailyList.get(i).getDateCalculated());
             switch (type) {
                 case("register_num"):yData.add(dailyList.get(i).getRegisterNum());
                     break;
                 case("login_num"):yData.add(dailyList.get(i).getLoginNum());
                 break;
                 case("course_num"):yData.add(dailyList.get(i).getCourseNum());
                 break;
                 case("video_view_num"):yData.add(dailyList.get(i).getVideoViewNum());
                 break;
             }
        }
        return R.ok().data("xData",xData).data("yData",yData);

    }
}

