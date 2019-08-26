package cn.etu.jxust.test;

import cn.etu.jxust.service.IAccountService;
import cn.etu.jxust.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ddh
 * @date 2019/8/26 9:12
 * @description 整合 junit 单元测试
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {
    @Autowired
    @Qualifier("proxyAccountService")
    private IAccountService as;

    @Test
    public void testTransfer() {
        as.transfer("aaa", "bbb", 100f);
    }
}
