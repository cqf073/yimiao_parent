package com.cqf.cmn;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/24 11:08
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
public class listening extends AnalysisEventListener<testEasyExcel> {
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }
    @Override
    //一行行读取 第二行读取
    public void invoke(testEasyExcel testEasyExcel, AnalysisContext analysisContext) {
        System.out.println(testEasyExcel);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
