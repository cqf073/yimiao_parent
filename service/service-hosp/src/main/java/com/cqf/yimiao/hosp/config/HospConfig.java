package com.cqf.yimiao.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/20 11:01
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@Configuration
@MapperScan("com.cqf.yimiao.hosp.mapper")
public class HospConfig {
}