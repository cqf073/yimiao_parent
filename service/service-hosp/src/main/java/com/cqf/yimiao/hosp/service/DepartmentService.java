package com.cqf.yimiao.hosp.service;


import com.cqf.yimiao.model.hosp.Department;
import com.cqf.yimiao.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/27 17:11
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
public interface DepartmentService {
    void saveDepartment(Map<String, Object> parameterMap);


    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);
//删除科室
    void remove(String hosCode, String depcode);
}
