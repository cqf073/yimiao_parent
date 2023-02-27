package com.cqf.yimiao.hosp.repository;

import com.cqf.yimiao.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/27 13:46
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {

    Hospital getHospitalByHoscode(String code);
}
