package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Model.Product;

public interface IProductservice {
    public Product findById(Long id);

    public Product saveProduct(Product product);

}
