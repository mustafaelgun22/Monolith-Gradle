package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceFactory {

    public ServiceFactory(JpaRepository repository) {
        services = new HashMap<>();
        services.put(ServiceEnum.productService, new ProductService(repository));
    }

    private Map<ServiceEnum, IService> services;
    public IService getService(ServiceEnum serviceEnum) {
        return services.get(serviceEnum);
    }
}

