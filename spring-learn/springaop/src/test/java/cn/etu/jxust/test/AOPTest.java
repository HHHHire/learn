package cn.etu.jxust.test;

import cn.etu.jxust.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ddh
 * @date 2019/8/26 15:36
 * @description
 **/
public class AOPTest {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = (IAccountService)ac.getBean("accountService");
        as.saveAccount();
//        as.updateAccount(1);
//        as.deleteAccount();
    }
}
