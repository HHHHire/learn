package com.ddh.learn.excel.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/14 13:15
 * @desc excel接口
 */
public interface ExcelService {
    /**
     * 导出excel到响应流中
     *
     * @param response 响应流
     */
    void export(HttpServletResponse response);

    /**
     * 导入excel
     *
     * @param multipartFile 文件
     */
    void importExcel(MultipartFile multipartFile);
}
