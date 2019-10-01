package cn.edu.jxust.serviceconsumer.controller;

import cn.edu.jxust.serviceconsumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: ddh
 * @Date: 2019/9/30 16:57
 * @Description:
 */
@Controller
@RequestMapping("consumer/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @GetMapping
    @ResponseBody
    public User queryUserById(@RequestParam("id") Long id) {
        /*List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        ServiceInstance instance = instances.get(0);*/
        return this.restTemplate.getForObject("http://service-provider/user/" + id, User.class);
    }
}
