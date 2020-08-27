package com.bupt.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:OneSubject</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/31 11:49
 * Version 1.0
 */
@Data
public class OneSubject {
    private String id;

    private String title;

    private List<TwoSubject> children = new ArrayList<>();
}
