package com.cqf.yimiao.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqf.yimiao.common.result.Result;
import com.cqf.yimiao.common.utils.MD5;
import com.cqf.yimiao.hosp.service.HospitalSetService;
import com.cqf.yimiao.model.hosp.HospitalSet;
import com.cqf.yimiao.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/18 18:33
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    //    测试表中的所有数据
    @GetMapping("findAll")
    @ApiOperation(value = "获取医院所有信息")
    public Result findAllHospital() {
        //调用service
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    //    删除一个医院设置数据
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id逻辑删除删除医院设置")
    public Result removeHospSet(@PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //    条件查询带上分页查询
    @ApiOperation(value = "条件查询带上分页查询")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPage(@PathVariable Long current,
                           @PathVariable Long limit,
                           @RequestBody(required = false) HospitalSetQueryVo hospitalQueryVo) {        //hospitalQueryVo 传入进来的查询条件hospname 和hospcode

        //创建page对象 传递currnet 和 limit
        Page<HospitalSet> page = new Page<>(current, limit);

        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hosname = hospitalQueryVo.getHosname();
        String hoscode = hospitalQueryVo.getHoscode();
        if (!StringUtils.isEmpty(hosname)) {
            wrapper.like("hosname", hospitalQueryVo.getHosname());
        }
        if (!StringUtils.isEmpty(hoscode)) {
            wrapper.eq("hoscode", hospitalQueryVo.getHoscode());
        }
        //调用方法
        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, wrapper);
        return Result.ok(hospitalSetPage);

    }


    //    添加医院设置接口
    @ApiOperation(value = "添加医院设置接口")
    @PostMapping("saveHospSet")
    public Result saveHospSet(@RequestBody(required = true) HospitalSet hospitalSet) {
        //设置状态
        hospitalSet.setStatus(1);
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
        boolean Saveishosp = hospitalSetService.save(hospitalSet);
        if (Saveishosp) {
            return Result.ok();
        } else {
            return Result.fail();
        }

    }

    //    根据id过获取医院设置接口
    @ApiOperation(value = "根据id过获取医院设置接口")
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    //    修改医院设置接口
    @ApiOperation(value = "修改医院设置接口")
    @PostMapping("UpdateHospSet")
    public Result UpdateHospSet(@RequestBody HospitalSet hospitalSet) {
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //    批量删除医院设置接口
    @ApiOperation(value = "批量删除医院设置接口")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idlist) {
        boolean flag = hospitalSetService.removeByIds(idlist);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

//    设置医院锁定接口
@ApiOperation(value = "设置医院锁定接口和解锁接口")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable long id,@PathVariable Integer status){
    HospitalSet hospitalSet = hospitalSetService.getById(id);
    hospitalSet.setStatus(status);
    boolean flag = hospitalSetService.updateById(hospitalSet);
    if (flag) {
        return Result.ok();
    } else {
        return Result.fail();
    }
}
//    发送签名key（签名的密钥）
    @PutMapping("SentKey/{id}")
    public Result SentKey(@PathVariable long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //todo 发送短信
        return  Result.ok();
    }
}
