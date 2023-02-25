package com.cqf.cmn;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/24 10:58
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
public class testWrite {

    public static void main(String[] args) {
        List<testEasyExcel> list = new ArrayList<>();
        for(int i = 1;i <=10;i++){
            testEasyExcel testEasyExcel = new testEasyExcel();
            testEasyExcel.setId(i);
            testEasyExcel.setName(i+"0"+"i");
            list.add(testEasyExcel);
        }
        String filename = "F:\\test\\01.xlsx";
        EasyExcel.write(filename,testEasyExcel.class).sheet("user info")
                .doWrite(list);
    }
}
