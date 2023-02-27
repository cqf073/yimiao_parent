package com.cqf.yimiao.hosp.controller.api;

import com.cqf.yimiao.common.exception.YimiaoException;
import com.cqf.yimiao.common.helper.HttpRequestHelper;
import com.cqf.yimiao.common.result.Result;
import com.cqf.yimiao.common.result.ResultCodeEnum;
import com.cqf.yimiao.common.utils.MD5;
import com.cqf.yimiao.hosp.service.HospitalService;
import com.cqf.yimiao.hosp.service.HospitalSetService;
import com.cqf.yimiao.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/27 13:50
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
@RestController
@RequestMapping("/api/hosp")
public class Apicontroller {
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private HospitalSetService hospitalSetService;

//    查询医院的接口
    @PostMapping("hospital/show")
    public Result getHospitalShow(HttpServletRequest request){
        //获取医院信息
        Map<String, String[]> map = request.getParameterMap();
        Map<String, Object> parameterMap = HttpRequestHelper.switchMap(map);
        //前端传来的签名
        String hosSign = (String)parameterMap.get("sign");
        String hosCode = (String)parameterMap.get("hoscode");
        //获取数据库中的sign
        String signKey = hospitalSetService.getSignKey(hosCode);
        //前端的sign加密
        String keySignMD5 = MD5.encrypt(signKey);
        //判断签名是否一致
        if(!hosSign.equals(keySignMD5)){
            throw  new YimiaoException(ResultCodeEnum.SIGN_ERROR);
        }
        Hospital hospital = hospitalService.getByHoscode(hosCode);
        return Result.ok(hospital);

    }


//    上传医院的接口
    @PostMapping("saveHospital")
    public Result saveHospital(HttpServletRequest request){
        //获取医院信息
        Map<String, String[]> map = request.getParameterMap();
        Map<String, Object> parameterMap = HttpRequestHelper.switchMap(map);
        //前端传来的签名
        String hosSign = (String)parameterMap.get("sign");
        String hosCode = (String)parameterMap.get("hoscode");
        //获取数据库中的sign
        String signKey = hospitalSetService.getSignKey(hosCode);
        //前端的sign加密
        String keySignMD5 = MD5.encrypt(signKey);
        //判断签名是否一致
        if(!hosSign.equals(keySignMD5)){
            throw  new YimiaoException(ResultCodeEnum.SIGN_ERROR);
        }
//        修改base46流的问题
            String logoDataString = (String)parameterMap.get("logoData");

            String logoData = logoDataString.replaceAll(" ", "+");
            parameterMap.put("logoData", logoData);


        hospitalService.save(parameterMap);
        return  Result.ok();

    }
}
