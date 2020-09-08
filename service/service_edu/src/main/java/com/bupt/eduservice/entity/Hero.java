package com.bupt.eduservice.entity;

import lombok.Data;

/**
 * <p>Title:Hero</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/9/2 16:14
 * Version 1.0
 */
@Data
public class Hero {
    private Integer id;
    private String name;

    public Hero(Integer id,String name) {
        this.id = id;
        this.name = name;
    }
}
