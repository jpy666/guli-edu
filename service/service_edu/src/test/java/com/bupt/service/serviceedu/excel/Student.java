package com.bupt.service.serviceedu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * <p>Title:Student</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/30 19:25
 * Version 1.0
 */
@Data
public class Student {
    //value属性指定表头
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
