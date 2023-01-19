package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Repository.GalleryRepository;
import com.sha.springbootmicro.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ServiceFactory {


    @Qualifier("productRepository")
    private ProductRepository productRepository;


    @Qualifier("galleryRepository")
    private GalleryRepository galleryRepository;

    public ServiceFactory(ProductRepository productRepository, GalleryRepository galleryRepository) {
        this.productRepository = productRepository;
        this.galleryRepository = galleryRepository;
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
}

