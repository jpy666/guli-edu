package com.bupt.eduorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.eduorder.entity.TradeOrder;
import com.bupt.eduorder.entity.TradePayLog;
import com.bupt.eduorder.mapper.TradePayLogMapper;
import com.bupt.eduorder.service.TradeOrderService;
import com.bupt.eduorder.service.TradePayLogService;
import com.bupt.eduorder.util.HttpClientUtils;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-08-06
 */
@Service
public class TradePayLogServiceImpl extends ServiceImpl<TradePayLogMapper, TradePayLog> implements TradePayLogService {
    @Autowired
    private TradeOrderService tradeOrderService;
    @Override
    public Map createNative(String orderNo) {
        //1根据orderNo查询出订单信息
        QueryWrapper<TradeOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        TradeOrder tradeOrder = tradeOrderService.getOne(queryWrapper);

        //调用接口设置参数
        Map<String,String> params = new HashMap<>();
        params.put("appid", "wxf913bfa3a2c7eeeb");//关联的公众号的appid
        params.put("mch_id", "1543338551");//商户号
        params.put("nonce_str", WXPayUtil.generateNonceStr());//生成随机字符串
        params.put("body", tradeOrder.getCourseTitle());
        params.put("out_trade_no", orderNo);

        //注意，这里必须使用字符串类型的参数（总金额：分）
        String totalFee = tradeOrder.getTotalFee().intValue() + "";
        params.put("total_fee", totalFee);

        params.put("spbill_create_ip", "127.0.0.1");
        params.put("notify_url", "http://imhelen.free.idcfengye.com/api/trade/weixin-pay/callback/notify");
        params.put("trade_type", "NATIVE");

        HttpClientUtils httpClientUtils = new HttpClientUtils("https://api.mch.weixin.qq.com/pay/unifiedorder");
        //将参数转化为xml格式
        try {
            String xml = WXPayUtil.generateSignedXml(params,"atguigu3b0kn9g5v426MKfHQH7X8rKwb");
            httpClientUtils.setXmlParam(xml);
            httpClientUtils.post();
            String content = httpClientUtils.getContent();
            Map<String, String> map = WXPayUtil.xmlToMap(content);
            Map<String,Object> result = new HashMap<>();
            result.put("result_code", map.get("result_code"));//响应码
            result.put("code_url", map.get("code_url"));//生成二维码的url
            result.put("course_id", tradeOrder.getCourseId());//课程id
            result.put("total_fee", tradeOrder.getTotalFee());//订单总金额
            result.put("out_trade_no", orderNo);//订单号
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        Map<String,String> params = new HashMap<>();
        params.put("appid", "wxf913bfa3a2c7eeeb");//关联的公众号的appid
        params.put("mch_id", "1543338551");//商户号
        params.put("nonce_str", WXPayUtil.generateNonceStr());//生成随机字符串
        params.put("out_trade_no", orderNo);

        HttpClientUtils httpClientUtils = new HttpClientUtils("https://api.mch.weixin.qq.com/pay/orderquery");
        try {
            httpClientUtils.setXmlParam(WXPayUtil.generateSignedXml(params,"atguigu3b0kn9g5v426MKfHQH7X8rKwb"));
            httpClientUtils.setHttps(true);
            httpClientUtils.post();
            String content = httpClientUtils.getContent();
            Map<String, String> map = WXPayUtil.xmlToMap(content);
            return map;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updatePayLog(Map<String, String> map) {
        //更新订单表状态
        String orderNo = map.get("out_trade_no");
        QueryWrapper<TradeOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        TradeOrder tradeOrder = tradeOrderService.getOne(queryWrapper);
        if(tradeOrder.getStatus().intValue() == 1) {
            return;
        }
        tradeOrder.setStatus(1);
        tradeOrderService.updateById(tradeOrder);
        //插入记录
        TradePayLog payLog = new TradePayLog();
        payLog.setOrderNo(orderNo);
        payLog.setPayTime(new Date());
        payLog.setPayType(1);//支付类型
        payLog.setTotalFee(Long.parseLong(map.get("total_fee")));//总金额(分)
        payLog.setTradeState(map.get("result_code"));//支付状态
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(new Gson().toJson(map));
        baseMapper.insert(payLog);
    }
}
