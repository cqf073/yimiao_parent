package com.cqf.yimiao.hosp.repository;

import com.cqf.yimiao.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/28 12:34
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {

    Schedule getDepartmentByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}
