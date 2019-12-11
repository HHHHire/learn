package com.example.springbootdemodockercompose.controller;

import com.example.springbootdemodockercompose.entity.Visitor;
import com.example.springbootdemodockercompose.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: ddh
 * @data: 2019/11/16 9:45
 * @description
 **/
@RestController
@RequestMapping("/visitor")
public class VisitorController {
    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorController(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @GetMapping
    public String getVisitTimes(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Visitor visitor = visitorRepository.findById(ip).orElse(null);
        if (visitor == null) {
            visitor = Visitor.builder()
                    .ip(ip)
                    .times(1).build();
        } else {
            visitor.setTimes(visitor.getTimes() + 1);
        }
        visitorRepository.save(visitor);
        return "ip: " + visitor.getIp() + " times: " + visitor.getTimes();
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello man!";
    }

    @GetMapping("/haha")
    public String asf(){
        return "123";
    }
}
