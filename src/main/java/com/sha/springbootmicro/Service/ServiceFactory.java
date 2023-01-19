package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Repository.ProductRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceFactory {

    public ServiceFactory(@Nullable ProductRepository productRepository) {
        services = new HashMap<>();
        services.put(ServiceEnum.productService, new ProductService(productRepository));
    }

    private Map<ServiceEnum, IService> services;
    public IService getService(ServiceEnum serviceEnum) {
        return services.get(serviceEnum);
    }
}

