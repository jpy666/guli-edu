package com.bupt.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * <p>Title:ExcelSubject</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/30 22:15
 * Version 1.0
 */
@Data
public class ExcelSubject {
    @ExcelProperty(index = 0)
    private String oneSubject;

    @ExcelProperty(index = 1)
    private String twoSubject;
}
