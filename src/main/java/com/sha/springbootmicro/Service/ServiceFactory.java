package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Repository.GalleryRepository;
import com.sha.springbootmicro.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceFactory {


    Map<ServiceEnum,IService> services = new HashMap<>();

    public ServiceFactory(List<IService> services) {
        services.put(ServiceEnum.productService, productService);
        services.put(ServiceEnum.galleryService, galleryService);
    }

    public IService getService(ServiceEnum type) {
        switch (type) {
            case productService:
                return new ProductService(productRepository);
            case galleryService:
                return new GalleryService(galleryRepository);
            default:
                throw new IllegalArgumentException("Invalid service type");
        }
    }

    public IService getService2(ServiceEnum type) {
        return services.get(type);
    }
}

