package com.bupt.eduorder.controller;

import com.bupt.commonutils.result.R;
import com.bupt.eduorder.service.TradeOrderService;
import com.bupt.eduorder.service.TradePayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-08-06
 */
@RestController
@RequestMapping("/eduorder/tradepay")
@CrossOrigin
public class TradePayLogController {
    @Autowired
    private TradeOrderService tradeOrderService;
    @Autowired
    private TradePayLogService tradePayLogService;
    @PostMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        Map map = tradePayLogService.createNative(orderNo);


        return R.ok().data(map);
    }

    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        Map<String,String> map = tradePayLogService.queryPayStatus(orderNo);

        if(map == null) {
            return R.error().message("支付失败");
        }
        if(map.get("trade_state").equals("SUCCESS")) {
            tradePayLogService.updatePayLog(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }
}

