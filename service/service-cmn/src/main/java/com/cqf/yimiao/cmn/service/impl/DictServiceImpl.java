package com.cqf.yimiao.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqf.yimiao.cmn.listener.DictListerner;
import com.cqf.yimiao.cmn.mapper.DictMapper;
import com.cqf.yimiao.cmn.service.DictService;
import com.cqf.yimiao.model.cmn.Dict;
import com.cqf.yimiao.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

//    根据id查找数据字列表
    @Override
    @Cacheable(value = "dict",keyGenerator = "keyGenerator")
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

    @Override
    public void exportDictExcel(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "dict";
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            //查询数据库
            List<Dict> dictList = baseMapper.selectList(null);
            //dict 转为dictvo
            ArrayList<DictEeVo> list = new ArrayList<>();
            for (Dict d: dictList
                 ) {
                DictEeVo dictEeVo = new DictEeVo();
                BeanUtils.copyProperties(d,dictEeVo);
                list.add(dictEeVo);
            }
            EasyExcel.write(response.getOutputStream(), DictEeVo.class)
                    .sheet("dict").doWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //    导入数据字典
    @Override
    @CacheEvict(value = "dict", allEntries=true)
    //清空缓存内容
    public void importDict(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListerner(baseMapper))
                    .sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查询该节点是否有子数据子节点
    private boolean isChildren(Long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        return count>0;
    }
}
