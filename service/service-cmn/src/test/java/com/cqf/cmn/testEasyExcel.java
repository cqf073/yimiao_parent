package com.cqf.cmn;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/24 10:56
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@Data
public class testEasyExcel {
    @ExcelProperty(value = "user id",index = 0)
    private  int id;
    @ExcelProperty(value = "user name",index = 1)
    private  String name;

}
