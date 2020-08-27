package com.bupt.service.serviceedu.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:TestEasyExcel</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/30 19:30
 * Version 1.0
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //指定写入文件的地址包括文件名
        String fileName = "F:\\write.xlsx";
        //写操作
       // EasyExcel.write(fileName,Student.class).sheet("学生表").doWrite(getList());
        EasyExcel.read(fileName,Student.class,new ExcelListener()).sheet().doRead();
    }

    public static List<Student> getList() {
        List<Student> list = new ArrayList<>();

        for(int i = 0;i < 10;i ++) {
            Student student = new Student();
            student.setSno(i);
            student.setSname("lucy" + i);
            list.add(student);
        }
        return list;
    }
}
