package com.ddh.learn.utils.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/14 0:17
 */
@Component
public class HttpTemplate {
    @Autowired
    private RestTemplate restTemplate;

    public void sendPostRequest(String url, List<Map<String, Object>> param, HttpHeaders headers) {
        HttpEntity<List<Map<String, Object>>> httpEntity = new HttpEntity<>(param, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Map body = response.getBody();

        }
    }

    /**
     * Get 请求，如果是路径变量，则直接拼接在url后面即可
     *
     * @param url
     * @param param
     */
    public void sendGetPathRequest(String url, Map<String, Object> param) {
        try {
            ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class, param);
            if (forEntity.getStatusCode() == HttpStatus.OK) {
                String body = forEntity.getBody();
                if (body != null) {
                    System.out.println(body);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendPostFormRequest(String url, MultiValueMap<String, String> param) {

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, param, String.class);
        if (stringResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            String body = stringResponseEntity.getBody();
            if (body != null) {
                System.out.println(body);
            }
        }
    }

    /**
     * 处理携带body类型的请求
     *
     * @param url    请求地址
     * @param method HttpMethod
     * @param param  body
     * @return response
     */
    public String sendExchangeBodyRequest(String url, HttpMethod method, Map<String, Object> param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(param, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, method, entity, String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        }
        return null;
    }

/*    public String sendExchangeFileRequest(String url, HttpMethod method, String filePath) {
        FileSystemResource resource = new FileSystemResource(new File(filePath));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("file", resource);
        restTemplate.exchange(url, method, )
    }*/

    /**
     * 普通请求：GET,POST
     *
     * @param url     请求地址
     * @param method  方法
     * @param param   参数
     * @param headers 请求头
     * @return 返回体
     */
    public String sendExchangeRequest(String url, HttpMethod method, MultiValueMap<String, Object> param, HttpHeaders headers) {
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, method, httpEntity, String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        }
        return null;
    }

    /**
     * url: http://localhost:8080/test
     * form 表单单参数，放入 MultiValueMap<String, Object> 中；
     *
     * url: http://localhost:8080/test?id={id}
     * 跟在地址后面的参数，也放入 MultiValueMap<String, Object> 中，不过map的key要和{id}中的值相同
     *
     * url: http://localhost:8080
     * body json 将值放入 Map<String, Object> 中
     *
     */

}
