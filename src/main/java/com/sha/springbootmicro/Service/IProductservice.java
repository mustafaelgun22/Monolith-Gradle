package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Model.Product;

import java.util.Optional;

public interface IProductservice {
    public Optional<Product> findbyid(Long id);

    public Product saveProduct(Product product);

}
