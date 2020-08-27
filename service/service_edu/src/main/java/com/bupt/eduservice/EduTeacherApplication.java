package com.bupt.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Title:EduTeacherApplication</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/5/26 20:31
 * Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bupt"})
@EnableDiscoveryClient
@EnableFeignClients
public class EduTeacherApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduTeacherApplication.class,args);
    }
}
