package com.bupt.eduorder.service.impl;

import com.bupt.commonutils.vo.CourseWeb;
import com.bupt.commonutils.vo.UcenterMemberVo;
import com.bupt.eduorder.client.EduClient;
import com.bupt.eduorder.client.MemberClient;
import com.bupt.eduorder.entity.TradeOrder;
import com.bupt.eduorder.mapper.TradeOrderMapper;
import com.bupt.eduorder.service.TradeOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.eduorder.util.OrderNoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-08-06
 */
@Service
public class TradeOrderServiceImpl extends ServiceImpl<TradeOrderMapper, TradeOrder> implements TradeOrderService {
    @Autowired
    private EduClient eduClient;
    @Autowired
    private MemberClient memberClient;
    @Override
    public String createOrder(String courseId, String memberId) {
        CourseWeb courseWeb = eduClient.getCourseInfoById(courseId);

        UcenterMemberVo ucenterMemberVo = memberClient.getMemberInfoById(memberId);

        TradeOrder tradeOrder = new TradeOrder();
        String orderNo = OrderNoUtils.getOrderNo();
        tradeOrder.setOrderNo(orderNo);
        tradeOrder.setCourseCover(courseWeb.getCover());
        tradeOrder.setCourseId(courseId);
        tradeOrder.setCourseTitle(courseWeb.getTitle());
        tradeOrder.setMemberId(memberId);
        tradeOrder.setMobile(ucenterMemberVo.getMobile());
        tradeOrder.setNickname(ucenterMemberVo.getNickname());
        tradeOrder.setStatus(0);
        tradeOrder.setPayType(1);
        tradeOrder.setIsDeleted(false);
        tradeOrder.setTeacherName(courseWeb.getTeacherName());
        tradeOrder.setTotalFee(courseWeb.getPrice());
        baseMapper.insert(tradeOrder);
        return tradeOrder.getOrderNo();
    }
}
