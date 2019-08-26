package cn.etu.jxust.service.impl;

import cn.etu.jxust.dao.IAccountDao;
import cn.etu.jxust.dao.impl.AccountDaoImpl;
import cn.etu.jxust.service.IAccountService;

/**
 * @author ddh
 * @date 2019/8/25 12:10
 * @description
 **/
public class AccountServiceImpl implements IAccountService {
//    private IAccountDao iAccountDao = (IAccountDao)BeanFactory.getBean("accountDao");
    private IAccountDao iAccountDao = new AccountDaoImpl()
        ;
//    public AccountServiceImpl(IAccountDao iAccountDao){
//        this.iAccountDao = iAccountDao;
//    }
    public void findAll() {
        iAccountDao.findAll();
    }
}
