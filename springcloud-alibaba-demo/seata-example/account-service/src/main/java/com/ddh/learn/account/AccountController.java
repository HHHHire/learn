package com.ddh.learn.account;

import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 用户扣钱
 *
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/7 17:34
 */
@RestController
public class AccountController {

    private final JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    private Random random;

    public AccountController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        random = new Random();
    }

    /**
     * 用户扣钱
     *
     * @param userId 用户id
     * @param money  金额
     */
    @PostMapping("/account")
    public String account(@RequestParam String userId, @RequestParam Integer money) {
        logger.info("Account Service begin ... xid is: {}", RootContext.getXID());

        if (random.nextBoolean()) {
            throw new RuntimeException("模拟异常");
        }

        int result = jdbcTemplate.update("update account_tbl set money = money - ? where user_id = ?", money, userId);

        logger.info("Account Service end ... ");

        if (result == 1) {
            return "success";
        }

        return "fail";
    }
}
