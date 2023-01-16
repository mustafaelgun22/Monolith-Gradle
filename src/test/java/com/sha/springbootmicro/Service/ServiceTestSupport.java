package com.sha.springbootmicro.Service;


import com.sha.springbootmicro.Model.Product;

public class ServiceTestSupport {

    public static Product generate_product(Long id){
        return new Product(id,"urun1",10.5,"deneme");
    }
}
