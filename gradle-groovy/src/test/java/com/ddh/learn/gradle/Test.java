package com.ddh.learn.gradle;

import com.ddh.learn.gradle.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/12 20:58
 */
public class Test {
   @org.junit.Test
   public void test1() {
       ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
       AccountService accountService = (AccountService) ac.getBean("accountService");
       accountService.findAll();
   }
}
