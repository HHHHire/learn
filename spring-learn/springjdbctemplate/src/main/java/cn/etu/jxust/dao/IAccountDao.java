package cn.etu.jxust.dao;

import cn.etu.jxust.domain.Account;

/**
 * @author ddh
 * @date 2019/8/26 17:24
 * @description
 **/
public interface IAccountDao {
    Account findAccountById(Integer accountId);

    Account findAccountByName(String name);

    void updateAccount(Account account);
}
