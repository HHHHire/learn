package cn.etu.jxust.ui;

import cn.etu.jxust.dao.IAccountDao;
import cn.etu.jxust.service.IAccountService;
import com.sun.org.apache.bcel.internal.generic.IADD;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author ddh
 * @date 2019/8/25 12:42
 * @description
 **/
public class Client {
    public static void main(String[] args) {
//        IAccountService as = new AccountServiceImpl();
//        as.findAll();
       /* for (int i = 0; i < 5; i++){
            IAccountService as = (IAccountService)BeanFactory.getBean("accountService");
            System.out.println(as);
        }*/
//        as.findAll();


        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
//        ApplicationContext ac = new FileSystemXmlApplicationContext("C:\\Users\\HP\\Desktop\\bean.xml");
        IAccountService as = (IAccountService)ac.getBean("accountService2");
//        IAccountDao ad = ac.getBean("accountDao", IAccountDao.class);

//        System.out.println(as);
//        System.out.println(ad);
        as.findAll();

    }
}
