package cn.edu.jxust.sort.service;

import cn.edu.jxust.sort.entity.po.Account;

/**
 * @author: ddh
 * @data: 2020/1/3 11:26
 * @description
 **/
public interface AccountService {
    Account getLogin(String adminName, String encrypt);

    Account createAccount(Account build);

    Integer deleteAccountByUserId(String userId);
}
