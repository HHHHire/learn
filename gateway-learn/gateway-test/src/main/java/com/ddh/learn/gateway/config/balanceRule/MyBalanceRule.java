package com.ddh.learn.gateway.config.balanceRule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/4 18:35
 */
@Component
public class MyBalanceRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        // 获取可用的服务
        List<Server> reachableServers = this.getLoadBalancer().getReachableServers();
        if (reachableServers.isEmpty()) {
            return null;
        }
        if (reachableServers.size() == 1) {
            return reachableServers.get(0);
        }
        // 如果key为空，返回随机的服务
        if (key == null) {
            return randomChoose(reachableServers);
        }
        return hashKeyChoose(reachableServers, key);
    }

    /**
     * 随机返回一个服务实例
     */
    private Server randomChoose(List<Server> servers) {
        int nextInt = RandomUtils.nextInt(servers.size());
        return servers.get(nextInt);
    }

    private Server hashKeyChoose(List<Server> servers, Object key) {
        int hashCode = Math.abs(key.hashCode());
        if (hashCode < servers.size()) {
            return servers.get(hashCode);
        }
        int index = hashCode % servers.size();
        return servers.get(index);

    }
}
