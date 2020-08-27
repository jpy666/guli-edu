package com.bupt.edusta.service;

import com.bupt.edusta.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-08-07
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createSta(String day);
}
