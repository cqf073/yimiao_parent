package com.cqf.yimiao.hosp.repository;

import com.cqf.yimiao.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/27 17:10
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
public interface DepartmentRepository extends MongoRepository<Department,String> {
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}
