package com.cqf.yimiao.hosp.service;

import com.cqf.yimiao.model.hosp.Schedule;
import com.cqf.yimiao.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/28 12:35
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
public interface ScheduleService {

    void saveSchedule(Map<String, Object> parameterMap);

    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    void removeSchedule(String hoscode, String hosScheduleId);
}
