package cn.etu.jxust.jdbctemplate;

import cn.etu.jxust.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

/**
 * @author ddh
 * @date 2019/8/26 16:41
 * @description
 **/
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jdbctemplate = ac.getBean("jdbctemplate", JdbcTemplate.class);
        jdbctemplate.update("update account set name = ?, money = ? where id = ?", "lisi", 1231, 4);


//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/bank");
//        ds.setUsername("root");
//        ds.setPassword("123456");
//
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(ds);
//
//        jdbcTemplate.execute("insert into account(name, money) values('zhangsan', 1200)");
        // 查询所有
//        List<Account> accounts = jdbctemplate.query("select * from account where money > ?", new BeanPropertyRowMapper<Account>(Account.class), 1000f);
//        for (Account account : accounts) {
//            System.out.println(account);
//        }

        // 查询返回一行一列
        Integer count = jdbctemplate.queryForObject("select count(*) from account where money > ?", Integer.class, 1000f);
        System.out.println(count);
    }
}
