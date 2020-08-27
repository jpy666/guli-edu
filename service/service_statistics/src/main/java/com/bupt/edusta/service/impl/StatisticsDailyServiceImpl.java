package com.bupt.edusta.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.commonutils.result.R;
import com.bupt.edusta.client.UcenterClient;
import com.bupt.edusta.entity.StatisticsDaily;
import com.bupt.edusta.mapper.StatisticsDailyMapper;
import com.bupt.edusta.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-08-07
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public void createSta(String day) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated",day);
        baseMapper.delete(queryWrapper);
        R result = ucenterClient.countResgister(day);
        Integer count = (Integer)result.getData().get("count");
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setRegisterNum(count);
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));

        baseMapper.insert(statisticsDaily);
    }
}
