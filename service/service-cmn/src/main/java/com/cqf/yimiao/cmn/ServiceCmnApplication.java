package com.cqf.yimiao.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/23 20:12
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.cqf"})
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class, args);
    }
}
