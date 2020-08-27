package com.bupt.eduorder.service;

import com.bupt.eduorder.entity.TradePayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-08-06
 */
public interface TradePayLogService extends IService<TradePayLog> {

    Map createNative(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updatePayLog(Map<String, String> map);
}
