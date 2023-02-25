package com.cqf.cmn;

import com.alibaba.excel.EasyExcel;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/24 11:14
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
public class testRead {
    public static void main(String[] args) {
        String filename = "F:\\test\\01.xlsx";
        EasyExcel.read(filename,testEasyExcel.class,new listening()).sheet().doRead();
    }
}
