package com.bupt.eduservice.entity.chapter;

import com.bupt.eduservice.entity.EduVideo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:ChapterVo</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/6/1 10:25
 * Version 1.0
 */
@Data
public class ChapterVo {
    private String id;

    private String title;

    List<EduVideo> children = new ArrayList<>();
}
