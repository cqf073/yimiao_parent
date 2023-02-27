package com.cqf.yimiao.hosp.service;

import com.cqf.yimiao.model.hosp.Hospital;

import java.util.Map;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/27 13:47
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
public interface HospitalService {
    void save(Map<String, Object> parameterMap);
    Hospital getByHoscode(String hoscode);
}
