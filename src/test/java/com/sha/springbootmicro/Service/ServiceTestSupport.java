package com.sha.springbootmicro.Service;


import com.sha.springbootmicro.Dto.ProductDto;
import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServiceTestSupport {

    private static ProductRepository productRepository ;

    public ServiceTestSupport(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static Product generate_product(Long id){
        return new Product(id,"urun1",10.5,"deneme");
    }
}
