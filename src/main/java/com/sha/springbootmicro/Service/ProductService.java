package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Dto.ProductDto;
import com.sha.springbootmicro.Exception.ProductNotFoundException;
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
    public Product findbyid(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product yok" + id));
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
        return repository.find_all_products_with_name(name);
    }

    public ProductDto get_product_dto(Long id){
        Product product=this.findbyid(id);
        return new ProductDto(product.getName(), product.getPrice());
    }


    // Stream, gerçekte bir veri kaynağını temsil etmez, ancak veri kaynağının öğelerine erişmenizi ve
    // bu öğeler üzerinde işlem yapmanızı sağlar. Stream işlemleri, veri kaynağının öğelerini değiştirmez,
    // yalnızca yeni bir veri kaynağı oluşturur.
    public List<ProductDto> get_products_dto(List<Product> products){
        return products.stream().map(product ->
            this.get_product_dto(product.getId())
            ).collect(Collectors.toList());
    }

}
