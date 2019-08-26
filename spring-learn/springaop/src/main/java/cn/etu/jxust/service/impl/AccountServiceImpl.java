package cn.etu.jxust.service.impl;

import cn.etu.jxust.service.IAccountService;

/**
 * @author ddh
 * @date 2019/8/26 15:26
 * @description
 **/
public class AccountServiceImpl implements IAccountService {
    public void saveAccount() {
        System.out.println("执行了保存");
        int i = 1/0;
    }

    public void updateAccount(int i) {
        System.out.println("执行了更新");
    }

    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
