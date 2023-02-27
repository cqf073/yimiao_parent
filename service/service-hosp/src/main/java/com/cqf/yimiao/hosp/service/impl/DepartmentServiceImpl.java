package com.cqf.yimiao.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqf.yimiao.hosp.repository.DepartmentRepository;
import com.cqf.yimiao.hosp.service.DepartmentService;
import com.cqf.yimiao.model.hosp.Department;
import com.cqf.yimiao.vo.hosp.DepartmentQueryVo;
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
 * @ 2023/2/27 17:11
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@Service
public class DepartmentServiceImpl  implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;


    //上传科室接口
    @Override
    public void saveDepartment(Map<String, Object> parameterMap) {
//        parameterMap转为department对象
        String jsonString = JSONObject.toJSONString(parameterMap);
        Department department = JSONObject.parseObject(jsonString,Department.class);
        //根据医院code和科室编号是否存在
        Department departmentExist = departmentRepository.
                getDepartmentByHoscodeAndDepcode(department.getHoscode(),department.getDepcode());
        if(departmentExist!= null){
            departmentExist.setUpdateTime(new Date());
            departmentExist.setIsDeleted(0);
            departmentRepository.save(departmentExist);
        }else{
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }

    }
    //查询所有的department
//    @Override
//    public Page<Department> findPageDepartment(int page, int limit , DepartmentQueryVo departmentQueryVo) {
////        page是第一页
//        Pageable pageable = PageRequest.of(page - 1, limit);
//        Department department = new Department();
//        BeanUtils.copyProperties(departmentQueryVo,department);
//        department.setIsDeleted(0);
//        ExampleMatcher matching = ExampleMatcher.matching()
//                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
//                                .withIgnoreCase(true);
//        Example<Object> example = Example.of(department, matching);
//        org.springframework.data.domain.Page<Department> all =departmentRepository.findAll(example,pageable);
//
//
//        return all;
//    }

//    查询所有的department
    @Override
    public Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo) {
        // 创建Pageable对象，设置当前页和每页记录数
        //0是第一页
        Pageable pageable = PageRequest.of(page-1,limit);
        // 创建Example对象
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo,department);
        department.setIsDeleted(0);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example<Department> example = Example.of(department,matcher);

        Page<Department> all = departmentRepository.findAll(example, pageable);
        return all;
    }
//删除科室
    @Override
    public void remove(String hosCode, String depcode) {
//   code 查询科室信息
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hosCode, depcode);
    if(department!=null){
        departmentRepository.deleteById(department.getId());
    }
    }


}
