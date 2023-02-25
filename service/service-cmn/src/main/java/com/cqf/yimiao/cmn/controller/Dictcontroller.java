package com.cqf.yimiao.cmn.controller;

import com.cqf.yimiao.cmn.service.DictService;
import com.cqf.yimiao.common.result.Result;
import com.cqf.yimiao.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/23 20:29
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@Api(value = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class Dictcontroller {

    @Autowired
    private DictService dictService;
    //    导入数据字典
    @ApiOperation("导入数据字典")
    @PostMapping("importDict")
    public Result importDict(MultipartFile file){
        dictService.importDict(file);
        return Result.ok();
    }


//    根据id 查询子数据列表
    @ApiOperation("根据id 查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id){
      List<Dict> list =   dictService.findChildData(id) ;
        return Result.ok(list);
    }

    //导出数据字典为excel
    @ApiOperation("导出数据字典为excel")
    @GetMapping("exportDictExcel")
    public void exportDictExcel(HttpServletResponse httpServletResponse){
        dictService.exportDictExcel(httpServletResponse);
    }


}
