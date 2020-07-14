package com.ddh.learn.excel.controller;

import com.ddh.learn.excel.service.ExcelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/14 13:17
 * @desc Excel控制层
 */
@RestController
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    /**
     * 导出Excel
     *
     * @param response 响应流
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        excelService.export(response);
    }

    /**
     * 导入Excel
     *
     * @param multipartFile 文件
     */
    @PostMapping("/import")
    public void importExcel(MultipartFile multipartFile) {
        excelService.importExcel(multipartFile);
    }

}
