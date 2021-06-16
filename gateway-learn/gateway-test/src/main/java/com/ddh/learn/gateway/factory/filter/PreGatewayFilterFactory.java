package com.ddh.learn.gateway.factory.filter;

import io.netty.handler.codec.http.HttpMethod;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/24 0:24
 * 前置过滤器
 */
public class PreGatewayFilterFactory extends AbstractGatewayFilterFactory<PreGatewayFilterFactory.Config> {

    private static final String TEMP = "temp";

    public PreGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
//        return Arrays.asList(TEMP);
        return Collections.singletonList(NAME_KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
//            ServerHttpRequest.Builder mutate = exchange.getRequest().mutate();
//            ServerHttpRequest request = exchange.getRequest();
//            return chain.filter(exchange.mutate().request(request).build());
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("id", "1");
            map.add("name", "zhangsna");
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpRequestDecorator newRequest = new ServerHttpRequestDecorator(request) {
                @Override
                public String getMethodValue() {
                    return HttpMethod.POST.name();
                }

                @Override
                public URI getURI() {
                    return UriComponentsBuilder.fromUri(request.getURI()).build().toUri();
//                            .queryParams(map).build().toUri();
                }

                @Override
                public Flux<DataBuffer> getBody() {
                    return Flux.empty();
                }
            };
            System.out.println(config.getTemp());
            System.out.println("hello world PreGatewayFilterFactory");
            return chain.filter(exchange.mutate().request(newRequest).build());
        };
    }

    // 参数
    public static class Config{
        private String temp;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }
    }
}
