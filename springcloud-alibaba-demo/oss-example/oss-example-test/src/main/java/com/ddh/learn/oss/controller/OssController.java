package com.ddh.learn.oss.controller;

import com.ddh.learn.oss.service.OssService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/9 23:45
 */
@RestController
public class OssController {
    private final OssService ossService;

    public OssController(OssService ossService) {
        this.ossService = ossService;
    }


}
