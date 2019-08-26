package cn.etu.jxust.dao.impl;

import cn.etu.jxust.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * @author ddh
 * @date 2019/8/25 20:23
 * @description
 **/
@Repository
public class AccountDaoImpl implements IAccountDao {
    public void findAll() {
        System.out.println("hello world !");
    }
}
