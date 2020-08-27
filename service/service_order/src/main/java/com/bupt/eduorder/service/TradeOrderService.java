package com.bupt.eduorder.service;

import com.bupt.eduorder.entity.TradeOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-08-06
 */
public interface TradeOrderService extends IService<TradeOrder> {

    String createOrder(String courseId, String memberId);
}
