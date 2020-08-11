package com.ddh.learn.order;

import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Random;

/**
 * 创建订单
 *
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/7 23:50
 */
@SuppressWarnings("unused")
@RestController
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final JdbcTemplate jdbcTemplate;
    private final RestTemplate restTemplate;
    private Random random;
    private static final String USER_ID = "U100001";
    private static final String COMMODITY_CODE = "C00321";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";


    public OrderController(JdbcTemplate jdbcTemplate, RestTemplate restTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.restTemplate = restTemplate;
        this.random = new Random();
    }

    @PostMapping(value = "/order", produces = "application/json")
    public String createOrder(@RequestParam("userId") String userId,
                              @RequestParam("commodityCode") String commodityCode,
                              @RequestParam("counts") Integer counts) {
        logger.info("Order Service begin ... xid is :{}", RootContext.getXID());

        // 计算总价 -.-
        Integer cost = calculate(commodityCode, counts);

        // 调用account服务，扣除用户余额
        invokerAccountService(cost);

        // 创建订单
        Order order = Order.builder().userId(userId)
                .commodityCode(commodityCode)
                .counts(counts)
                .money(cost).build();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "insert into order_tbl(user_id, commodity_code, counts, money) value (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setObject(1, order.userId);
            ps.setObject(2, order.commodityCode);
            ps.setObject(3, order.getCounts());
            ps.setObject(4, order.getMoney());
            return ps;
        }, keyHolder);
        order.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        if (random.nextBoolean()) {
            throw new RuntimeException("模拟异常");
        }

        logger.info("Order Service end ... Created Oder: {}", order);

        if (result == 1) {
            return SUCCESS;
        }

        return FAIL;
    }

    private void invokerAccountService(Integer cost) {
        String url = "http://localhost:9030/account";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userId", USER_ID);
        map.add("money", String.valueOf(cost));
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
    }

    private Integer calculate(String commodityCode, Integer count) {
        return 2 * count;
    }
}
