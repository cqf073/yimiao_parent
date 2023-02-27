package com.cqf.yimiao.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqf.yimiao.hosp.mapper.HospitalSetMapper;
import com.cqf.yimiao.hosp.service.HospitalSetService;
import com.cqf.yimiao.model.hosp.HospitalSet;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/16 14:17
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper,HospitalSet> implements HospitalSetService {

    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode", hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);


        return hospitalSet.getSignKey();
    }
}
