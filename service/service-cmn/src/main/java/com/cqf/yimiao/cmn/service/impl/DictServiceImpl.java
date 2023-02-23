package com.cqf.yimiao.cmn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqf.yimiao.cmn.mapper.DictMapper;
import com.cqf.yimiao.cmn.service.DictService;
import com.cqf.yimiao.model.cmn.Dict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/16 14:17
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Dict> dictList = baseMapper.selectList(wrapper);
        //list对象设置haschild的值
        for (Dict dict:dictList
             ) {
            Long dictId = dict.getId();
            boolean isChildren = this.isChildren(dictId);
            dict.setHasChildren(isChildren);
        }
        return dictList;
    }
    //查询该节点是否有子数据子节点
    private boolean isChildren(Long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        return count>0;
    }
}
