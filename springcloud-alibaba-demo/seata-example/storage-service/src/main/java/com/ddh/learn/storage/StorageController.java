package com.ddh.learn.storage;

import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 更新库存
 *
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/8 0:42
 */
@RestController
public class StorageController {
    private Logger logger = LoggerFactory.getLogger(StorageController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private Random random;
    private final JdbcTemplate jdbcTemplate;

    public StorageController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.random = new Random();
    }

    @GetMapping(value = "/storage/{commodityCode}/{counts}", produces = "application/json")
    public String updateStorage(@PathVariable String commodityCode, @PathVariable Integer counts) {
        logger.info("Storage Service begin ... xid is : {}", RootContext.getXID());
        int result = jdbcTemplate.update("update storage_tbl set counts = counts - ? where commodity_code = ?",
                new Object[]{counts, commodityCode});
        logger.info("Storage Service end ... ");
        if (result == 1) {
            return SUCCESS;
        }
        return FAIL;
    }
}
