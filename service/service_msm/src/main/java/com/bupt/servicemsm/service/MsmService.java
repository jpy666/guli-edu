package com.bupt.servicemsm.service;

import java.util.Map;

/**
 * <p>Title:MsmService</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/7/9 12:10
 * Version 1.0
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
