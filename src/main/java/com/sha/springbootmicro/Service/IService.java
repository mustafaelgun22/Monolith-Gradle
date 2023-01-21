package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;


public interface IService<T> {

    public Optional<T> findById(Long id);
    public T saveEntity(T entity);


}
