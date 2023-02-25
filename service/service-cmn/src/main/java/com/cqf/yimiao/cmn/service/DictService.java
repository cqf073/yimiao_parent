package com.cqf.yimiao.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqf.yimiao.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author：cqf
 * @ 2023/2/16 14:14
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 **/
public interface DictService extends IService<Dict> {
    List<Dict> findChildData(Long id);
    void exportDictExcel(HttpServletResponse httpServletResponse);

    void importDict(MultipartFile multipartFile);
}
