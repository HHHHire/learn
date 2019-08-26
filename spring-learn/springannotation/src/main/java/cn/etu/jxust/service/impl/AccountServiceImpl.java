package cn.etu.jxust.service.impl;

import cn.etu.jxust.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * @author ddh
 * @date 2019/8/25 20:23
 * @description
 **/
@Service
public class AccountServiceImpl implements IAccountService {
    public void findAll() {
        System.out.println("service");
    }
}
