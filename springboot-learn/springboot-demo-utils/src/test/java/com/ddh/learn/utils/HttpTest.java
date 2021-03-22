package com.ddh.learn.utils;

import com.ddh.learn.utils.http.HttpTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/14 0:55
 */
public class HttpTest extends BaseTest {

    @Autowired
    private HttpTemplate httpTemplate;

    private static String url = "http://localhost:8080";

    @Test
    public void testGet() {
        url = url + "/get/v1?id={id}";
        Map<String, Object> param = new HashMap<>();
        param.put("id", "name");
        httpTemplate.sendGetPathRequest(url, param);
    }

    @Test
    public void testGet2() {
        url = url + "/get/v3/id?name={name}";
        Map<String, Object> param = new HashMap<>();
        param.put("name", "name");
        httpTemplate.sendGetPathRequest(url, param);
    }

    @Test
    public void testPost1() {
        url = url + "/post/v1/form";
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
//        param.add("id", "id");
        param.add("name", "name");
//        param.add("address", "address1");
//        param.add("address", "address2");
//        param.add("address", "address3");
        httpTemplate.sendPostFormRequest(url, param);
    }

    @Test
    public void testPost2() {
        url = url + "/post/v2";
        String json = "";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

    }

    @Test
    public void testGet3() {
    }
}
