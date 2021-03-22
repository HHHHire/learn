package com.ddh.learn.utils.controller;

import com.ddh.learn.utils.model.request.UserRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/14 0:42
 */
@RestController
public class TestController {

    @GetMapping("/get/v1")
    public String testGetV11() {
        return "success";
    }

    @GetMapping("/get/v1/{id}")
    public String testGetV1(@PathVariable String id) {
        return id;
    }

    @GetMapping("/get/v2")
    public String testGetV2(@RequestParam(value = "id") String id) {
        return id;
    }

    @GetMapping("/get/v3/{id}")
    public String testGetV3(@PathVariable String id, @RequestParam(value = "name") String name) {
        return name;
    }

    @PostMapping("/post/v1")
    public String testPostV1(@RequestBody UserRequest user) {
        return "success";
    }

    @PostMapping("/post/v1/form")
    public String testPostv1Form(@RequestParam(value = "name") String name) {
        return "success";
    }

    @PostMapping("/post/v2")
    public String testPostV2(@RequestParam(value = "name") String name, @RequestBody UserRequest user) {
        return "success";
    }

}
