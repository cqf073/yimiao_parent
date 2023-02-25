package com.cqf.yimiao.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.cqf.yimiao.cmn.mapper.DictMapper;
import com.cqf.yimiao.model.cmn.Dict;
import com.cqf.yimiao.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/24 15:22
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
public class DictListerner extends AnalysisEventListener<DictEeVo> {
//    一行行读取
    private DictMapper dictMapper;

    public DictListerner(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
