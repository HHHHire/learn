package com.ddh.learn.business;

import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/8 13:27
 */
@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "unused"})
@RestController
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);
    private static final String SUCCESS = "success";
    private static final String USER_ID = "U100001";
    private static final String COMMODITY_CODE = "C00321";
    private static final Integer COUNTS = 2;
    private final RestTemplate restTemplate;
    private final OrderClient orderClient;
    private final StorageClient storageClient;

    public HomeController(RestTemplate restTemplate, OrderClient orderClient, StorageClient storageClient) {
        this.restTemplate = restTemplate;
        this.orderClient = orderClient;
        this.storageClient = storageClient;
    }

    /**
     * 通过rest调用
     */
    @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx")
    @GetMapping(value = "/seata/rest", produces = "application/json")
    public String rest() {
        String storageResult = restTemplate.getForObject(
                "http://localhost:9032/storage/" + COMMODITY_CODE + "/" + COUNTS,
                String.class);
        if (!SUCCESS.equals(storageResult)) {
            throw new RuntimeException();
        }

        String url = "http://localhost:9031";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userId", USER_ID);
        map.add("commodity_code", COMMODITY_CODE);
        map.add("counts", String.valueOf(COUNTS));

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response;
        try {
            response = restTemplate.postForEntity(url, httpEntity, String.class);
        } catch (RestClientException e) {
            throw new RuntimeException("模拟异常");
        }

        if (!SUCCESS.equals(response.getBody())) {
            throw new RuntimeException();
        }

        return SUCCESS;
    }

    /**
     * 通过feign调用
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    @GetMapping(value = "/seata/feign", produces = "application/json")
    public String feign() {
        String storageResult = storageClient.updateStorage(COMMODITY_CODE, COUNTS);
        if (!SUCCESS.equals(storageResult)) {
            throw new RuntimeException();
        }

        String orderResult = orderClient.createOrder(USER_ID, COMMODITY_CODE, COUNTS);
        if (!SUCCESS.equals(orderResult)) {
            throw new RuntimeException();
        }
        return SUCCESS;
    }
}
