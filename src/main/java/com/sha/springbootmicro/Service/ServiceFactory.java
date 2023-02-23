package com.sha.springbootmicro.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceFactory {


    Map<ServiceEnum,IService> servicesmap = new HashMap<>();

    public ServiceFactory(List<IService> services) {
        services.forEach(x-> servicesmap.put(x.getType(),x));

    }

    public IService getService(ServiceEnum type) {
        return servicesmap.get(type);
    }
}

