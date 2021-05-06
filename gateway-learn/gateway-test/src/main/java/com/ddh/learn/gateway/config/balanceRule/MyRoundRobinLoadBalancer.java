package com.ddh.learn.gateway.config.balanceRule;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.reactive.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.reactive.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.reactive.Request;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.cloud.loadbalancer.core.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/6 16:10
 * @des: 自定义负载策略， 参照 {@link RoundRobinLoadBalancer}
 * 这里原本想参照 {@link RoundRobinLoadBalancer} 来重写choose和getInstanceResponse的，但是请看构造函数里的两个参数从 Environment 中获取失败，
 * 所以采用了另一种方式：MyReactiveLoadBalancerClientFilter + MyRoundRobinLoadBalancer
 *
 */
public class MyRoundRobinLoadBalancer extends RoundRobinLoadBalancer {

    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public MyRoundRobinLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
        super(serviceInstanceListSupplierProvider, serviceId);
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        if (serviceInstanceListSupplierProvider != null) {
            ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
                    .getIfAvailable(NoopServiceInstanceListSupplier::new);
            return supplier.get().next().map(this::getInstanceResponse);
        }
        return null;
    }

    /**
     * 具体的策略
     * @param instances 所有可用的服务实例
     */
    private Response<ServiceInstance> getInstanceResponse(
            List<ServiceInstance> instances) {
        if (instances.isEmpty()) {
            return new EmptyResponse();
        }

        ServiceInstance instance = instances.get(0);

        return new DefaultResponse(instance);
    }
}
