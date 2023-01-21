package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Repository.GalleryRepository;
import com.sha.springbootmicro.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceFactory {


    @Qualifier("productRepository")
    private ProductRepository productRepository;
    @Qualifier("galleryRepository")
    private GalleryRepository galleryRepository;

    Map<ServiceEnum,IService> services = new HashMap<>();

    public ServiceFactory(ProductRepository productRepository, GalleryRepository galleryRepository) {
        this.productRepository = productRepository;
        this.galleryRepository = galleryRepository;
        services.put(ServiceEnum.productService,new ProductService(productRepository));
        services.put(ServiceEnum.galleryService,new GalleryService(galleryRepository));
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

