package cn.etu.jxust.ui;

import cn.etu.jxust.dao.IAccountDao;
import cn.etu.jxust.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ddh
 * @date 2019/8/25 20:24
 * @description
 **/
public class Client {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = (IAccountService)ac.getBean("accountServiceImpl");
        IAccountDao ad = (IAccountDao)ac.getBean("accountDaoImpl");
        System.out.println(as);
        System.out.println(ad);
    }
}
