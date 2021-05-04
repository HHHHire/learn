package com.ddh.learn.gateway.filter;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/5 10:15
 */
@Component
public class UserLoadBalancerClientFilter extends LoadBalancerClientFilter {

    public UserLoadBalancerClientFilter(LoadBalancerClient loadBalancer, LoadBalancerProperties properties) {
        super(loadBalancer, properties);
    }

    @Override
    protected ServiceInstance choose(ServerWebExchange exchange) {
        String userId = exchange.getRequest().getHeaders().getFirst("userId");
        if (userId == null) {
            return super.choose(exchange);
        }

        if (this.loadBalancer instanceof RibbonLoadBalancerClient) {
            RibbonLoadBalancerClient client = (RibbonLoadBalancerClient) this.loadBalancer;
            String serviceId = ((URI) exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR)).getHost();

            return client.choose(serviceId, userId);
        }
        return super.choose(exchange);
    }
}
