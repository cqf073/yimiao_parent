package com.cqf.yimiao.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqf.yimiao.hosp.repository.ScheduleRepository;
import com.cqf.yimiao.hosp.service.ScheduleService;
import com.cqf.yimiao.model.hosp.Schedule;
import com.cqf.yimiao.vo.hosp.ScheduleQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
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
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void saveSchedule(Map<String, Object> parameterMap) {
        //查询是否存在
        String jsonString = JSONObject.toJSONString(parameterMap);
        Schedule  schedule = JSONObject.parseObject(jsonString,Schedule.class);
        //根据医院code和排班编号是否存在
        Schedule ScheduleExist = scheduleRepository.
                getDepartmentByHoscodeAndHosScheduleId(schedule.getHoscode(),schedule.getHosScheduleId());
        if(ScheduleExist!= null){
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            schedule.setStatus(1);
            scheduleRepository.save(schedule);
        }else{
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            schedule.setStatus(1);
            scheduleRepository.save(schedule);
        }
    }

    //查询排班接口
    @Override
    public Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo) {
        // 创建Pageable对象，设置当前页和每页记录数
        //0是第一页
        Pageable pageable = PageRequest.of(page-1,limit);
        // 创建Example对象
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleQueryVo,schedule);
        schedule.setIsDeleted(0);
        schedule.setStatus(1);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example<Schedule> example = Example.of(schedule,matcher);

        Page<Schedule> all = scheduleRepository.findAll(example, pageable);
        return all;
    }


    //移除排班信息
    @Override
    public void removeSchedule(String hoscode, String hosScheduleId) {
        //查询当前的信息
        Schedule schedule = scheduleRepository.getDepartmentByHoscodeAndHosScheduleId(hoscode, hosScheduleId);
        if (schedule!=null){
            scheduleRepository.deleteById(schedule.getId());
        }
    }
}
