package com.bupt.serviceucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Title:UcenterMemberApplication</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/7/10 9:30
 * Version 1.0
 */
@SpringBootApplication
@ComponentScan({"com.bupt"})
@MapperScan({"com.bupt.serviceucenter.mapper"})
@EnableDiscoveryClient
public class UcenterMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterMemberApplication.class,args);
    }
}
