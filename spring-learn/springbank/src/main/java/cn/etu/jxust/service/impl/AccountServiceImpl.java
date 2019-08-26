package cn.etu.jxust.service.impl;


import cn.etu.jxust.dao.IAccountDao;
import cn.etu.jxust.domain.Account;
import cn.etu.jxust.service.IAccountService;
import cn.etu.jxust.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;
    private TransactionManager txManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.txManager = transactionManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {

            // 执行操作
            List<Account> accounts = accountDao.findAllAccount();

            // 返回结果
            return accounts;

    }

    @Override
    public Account findAccountById(Integer accountId) {

            // 执行操作
            return accountDao.findAccountById(accountId);


    }

    @Override
    public void saveAccount(Account account) {

            // 执行操作
            accountDao.saveAccount(account);

    }

    @Override
    public void updateAccount(Account account) {
        // 执行操作`
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer acccountId) {
            // 执行操作
            accountDao.deleteAccount(acccountId);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
            // 执行操作
            // 转出账户
            Account sourceAccount = accountDao.findAccountByName(sourceName);
            // 转入账户
            Account targetAccount = accountDao.findAccountByName(targetName);
            // 转出账户减钱
            sourceAccount.setMoney(sourceAccount.getMoney() - money);
//            int i = 1/0;
            // 转入账户加钱
            targetAccount.setMoney(targetAccount.getMoney() + money);
            // 更新
            accountDao.updateAccount(sourceAccount);
            accountDao.updateAccount(targetAccount);
    }
}
