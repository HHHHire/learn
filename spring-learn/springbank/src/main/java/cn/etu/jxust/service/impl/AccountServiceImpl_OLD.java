package cn.etu.jxust.service.impl;


import cn.etu.jxust.dao.IAccountDao;
import cn.etu.jxust.domain.Account;
import cn.etu.jxust.service.IAccountService;
import cn.etu.jxust.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl_OLD implements IAccountService {

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
        try {
            // 开启事务
            txManager.begin();
            // 执行操作
            List<Account> accounts = accountDao.findAllAccount();
            // 提交事务
            txManager.commit();
            // 返回结果
            return accounts;
        } catch (Exception e) {
            // 回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 释放连接
            txManager.release();
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            // 开启事务
            txManager.begin();
            // 执行操作
            Account account = accountDao.findAccountById(accountId);
            // 提交事务
            txManager.commit();
            // 返回结果
            return account;
        } catch (Exception e) {
            // 回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 释放连接
            txManager.release();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            // 开启事务
            txManager.begin();
            // 执行操作
            accountDao.saveAccount(account);
            // 提交事务
            txManager.commit();
        } catch (Exception e) {
            // 回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 释放连接
            txManager.release();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            // 开启事务
            txManager.begin();
            // 执行操作
            accountDao.updateAccount(account);
            // 提交事务
            txManager.commit();
        } catch (Exception e) {
            // 回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 释放连接
            txManager.release();
        }
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        try {
            // 开启事务
            txManager.begin();
            // 执行操作
            accountDao.deleteAccount(acccountId);
            // 提交事务
            txManager.commit();
        } catch (Exception e) {
            // 回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 释放连接
            txManager.release();
        }
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        try {
            // 开启事务
            txManager.begin();
            // 执行操作
            // 转出账户
            Account sourceAccount = accountDao.findAccountByName(sourceName);
            // 转入账户
            Account targetAccount = accountDao.findAccountByName(targetName);
            // 转出账户减钱
            sourceAccount.setMoney(sourceAccount.getMoney() - money);
            int i = 1/0;
            // 转入账户加钱
            targetAccount.setMoney(targetAccount.getMoney() + money);
            // 更新
            accountDao.updateAccount(sourceAccount);
            accountDao.updateAccount(targetAccount);
            // 提交事务
            txManager.commit();
        } catch (Exception e) {
            // 回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            // 释放连接
            txManager.release();
        }

    }
}
