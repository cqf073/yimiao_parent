package com.cqf.yimiao.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqf.yimiao.hosp.repository.HospitalRepository;
import com.cqf.yimiao.hosp.service.HospitalService;
import com.cqf.yimiao.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void save(Map<String, Object> parameterMap) {
        //map转对象
        String mapString = JSONObject.toJSONString(parameterMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);
        String hostSign = (String) parameterMap.get("sign");

        String hascode = (String)parameterMap.get("hascode");
        //判断是否存在相同数据
        String hoscode = hospital.getHoscode();
        Hospital hospitalExist  = hospitalRepository.getHospitalByHoscode(hoscode);
        if(hospitalExist!=null){
            //修改
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);

        }else {
            //添加
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);
        return hospital;
    }
}
