package com.bupt.edusta.task;

import com.bupt.edusta.service.StatisticsDailyService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>Title:StaTask</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/8/7 20:03
 * Version 1.0
 */
@Component
public class StaTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task1() {
        String day = new DateTime().minusDays(1).toString("yyyy-MM-dd");
        statisticsDailyService.createSta(day);
    }
}
