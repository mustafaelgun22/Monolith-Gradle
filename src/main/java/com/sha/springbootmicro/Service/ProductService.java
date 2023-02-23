package com.sha.springbootmicro.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sha.springbootmicro.Dto.ProductDto;
import com.sha.springbootmicro.Exception.ProductNotFoundException;
import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IService<ProductDto,Product> {

    private final static String Not_Found_msg = "%s product not not";

    @Autowired
    private ObjectMapper objectMapper;

    @Qualifier("productRepository")
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(String.format(Not_Found_msg, id))));
    }

    @Override
    public Product saveEntity(Product product){
        return repository.save(product);
    }

    public List<Optional<Product>> filterByIds(List<Long> ids) {

        List<Optional<Product>> products = ids.stream().
                map(product -> repository.findById(product)).
                collect(Collectors.toList());
        products.remove(Optional.empty());
        return products;
    }

    public void deleteObjects(List<Long> ids){
        for (Long id :ids
             ) {
            repository.deleteById(id);
        }
    }

    public void deleteObject(Long id){
        repository.deleteById(id);
    }

    public List<Product> getAllObjects(){
        return repository.findAll();
    }

    public ProductDto getDto(Long id){
        Product product=this.findById(id).orElseThrow(()->new IllegalArgumentException("Product not found"));
        return new ProductDto(product.getName(), product.getPrice());
    }


    // Stream, gerçekte bir veri kaynağını temsil etmez, ancak veri kaynağının öğelerine erişmenizi ve
    // bu öğeler üzerinde işlem yapmanızı sağlar. Stream işlemleri, veri kaynağının öğelerini değiştirmez,
    // yalnızca yeni bir veri kaynağı oluşturur.
    public List<ProductDto> get_objects_dto(List<Product> products){
        return products.stream().map(product ->
            this.getDto(product.getId())
            ).collect(Collectors.toList());
    }

    public void updateObject(Map<String, Object> updates, Optional<Product> optionalProduct){
        Product product = optionalProduct.orElseThrow(()-> new IllegalArgumentException("Product not found"));
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(product.getClass(), key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, product, value);
        });
        repository.save(product);
    }

    public Optional<Product> addAttributes(Long id, Product product, Map<String,Object> attributes) {
        if(product.getAttributes()!=null) {
            Map<String,Object> exist_attibutes=product.getAttributes();
            for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
                exist_attibutes.putIfAbsent(attribute.getKey(), attribute.getValue());
            }
            product.setAttributes(exist_attibutes);
        }
        else{
            product.setAttributes(attributes);
        }
        repository.save(product);
        Optional<Product> product1=this.findById(id);
        return product1;
    }

    @Override
    public ServiceEnum getType() {
        return ServiceEnum.productService;
    }

}
