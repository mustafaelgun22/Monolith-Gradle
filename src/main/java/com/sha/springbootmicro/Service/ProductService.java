package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductservice{
    @Autowired
    private ProductRepository repository;
    @Override
    public Optional<Product> findbyid(Long id) {
        Optional<Product> product=repository.findById(id);
        return product;
    }

    @Override
    public Product saveProduct(Product product){
        Product created_product=repository.save(product);
        return created_product;
    }

    public List<Optional<Product>> filterbyids(List<Long> ids) {

        List<Optional<Product>> products = ids.stream().
                map(product -> repository.findById(product)).
                collect(Collectors.toList());
        products.remove(Optional.empty());
        return products;
    }

    public void deleteProduct(List<Long> ids){
        for (Long id :ids
             ) {
            repository.deleteById(id);
        }
    }

    public List<?> get_all_products(){
        return repository.findAll();
    }

    public List<Product> find_all_products(){
        return repository.find_all_products();
    }


    public List<Product> find_all_products_with_name(String name ){
        System.out.println(name);
        return repository.find_all_products_with_name(name);
    }


}
