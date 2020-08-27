package com.bupt.serviceucenter.entity.vo;

import lombok.Data;

/**
 * <p>Title:RegisterVo</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/7/10 9:43
 * Version 1.0
 */
@Data
public class RegisterVo {
    private String nickname;
    private String password;
    private String code;
    private String mobile;
}
