package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Dto.ProductDto;
import com.sha.springbootmicro.Exception.ProductNotFoundException;
import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IService {

    private final static String Not_Found_msg = "%s product not not";

    private JpaRepository<Product,Long> repository;

    public ProductService(JpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(String.format(Not_Found_msg,id)));
    }

    @Override
    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public List<Optional<Product>> filterByIds(List<Long> ids) {

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

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public ProductDto getProductDto(Long id){
        Product product=this.findById(id);
        return new ProductDto(product.getName(), product.getPrice());
    }


    // Stream, gerçekte bir veri kaynağını temsil etmez, ancak veri kaynağının öğelerine erişmenizi ve
    // bu öğeler üzerinde işlem yapmanızı sağlar. Stream işlemleri, veri kaynağının öğelerini değiştirmez,
    // yalnızca yeni bir veri kaynağı oluşturur.
    public List<ProductDto> get_products_dto(List<Product> products){
        return products.stream().map(product ->
            this.getProductDto(product.getId())
            ).collect(Collectors.toList());
    }

}
