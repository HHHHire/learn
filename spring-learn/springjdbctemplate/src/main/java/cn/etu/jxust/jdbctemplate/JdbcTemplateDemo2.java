package cn.etu.jxust.jdbctemplate;

import cn.etu.jxust.dao.IAccountDao;
import cn.etu.jxust.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ddh
 * @date 2019/8/26 17:33
 * @description
 **/
public class JdbcTemplateDemo2 {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountDao accountDao = (IAccountDao)ac.getBean("accountDao");

        Account account = accountDao.findAccountById(1);
        System.out.println(account);

    }
}
