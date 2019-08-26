package com.itheima.service;

import com.itheima.domain.Account;

/**
 * @author ddh
 * @date 2019/8/26 18:30
 * @description
 **/
public interface IAccountService {
    Account findAccountById(Integer accountId);

    void transfer(String sourceName, String targetName, Float money);
}
