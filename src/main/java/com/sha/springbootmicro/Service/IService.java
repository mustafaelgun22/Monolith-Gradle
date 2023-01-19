package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Model.Product;

import java.util.Optional;

public interface IService {

    public <T> T findById(Long id);

    public <T> T saveEntity(Product product);

}
