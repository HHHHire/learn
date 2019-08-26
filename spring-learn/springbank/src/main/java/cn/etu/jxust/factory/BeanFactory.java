package cn.etu.jxust.factory;

import cn.etu.jxust.domain.Account;
import cn.etu.jxust.service.IAccountService;
import cn.etu.jxust.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @author ddh
 * @date 2019/8/26 11:49
 * @description
 **/
public class BeanFactory {
    private IAccountService accountService;
    private TransactionManager txManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.txManager = transactionManager;
    }

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取 Service 代理对象
     */
    public IAccountService getAccountService() {
        return (IAccountService)Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtValue = null;
                        try {
                            // 开启事务
                            txManager.begin();
                            method.invoke(accountService, args);
                            // 提交事务
                            txManager.commit();
                            // 返回结果
                            return rtValue;
                        } catch (Exception e) {
                            // 回滚操作
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            // 释放连接
                            txManager.release();
                        }
                    }
                });
    }

}
