package com.cqf.yimiao.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/16 13:47
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.cqf")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
