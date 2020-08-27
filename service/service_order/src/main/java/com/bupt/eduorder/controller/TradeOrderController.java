package com.bupt.eduorder.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bupt.commonutils.result.R;
import com.bupt.commonutils.util.JWTUtils;
import com.bupt.eduorder.entity.TradeOrder;
import com.bupt.eduorder.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-08-06
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class TradeOrderController {
    @Autowired
    private TradeOrderService tradeOrderService;

    @PostMapping("/createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request) {
        String memberId = JWTUtils.getMemberIdByJwtToken(request);
        String orderNo = tradeOrderService.createOrder(courseId,memberId);
        return R.ok().data("orderNo",orderNo);
    }

    @GetMapping("getOrderInfo/{orderNo}")
    public R getOrderInfo(@PathVariable String orderNo) {
        QueryWrapper<TradeOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        TradeOrder tradeOrder = tradeOrderService.getOne(queryWrapper);
        return R.ok().data("tradeOrder",tradeOrder);
    }

    @GetMapping("isBuy/{courseId}/{memberId}")
    public boolean isBuy(@PathVariable String courseId,@PathVariable String memberId) {
        QueryWrapper<TradeOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",memberId);
        queryWrapper.eq("status",1);
        int count = tradeOrderService.count(queryWrapper);
        if(count > 0) {
            return true;
        }
        return false;
    }
}

