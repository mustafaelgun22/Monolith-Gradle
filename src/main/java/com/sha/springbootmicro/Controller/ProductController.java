package com.sha.springbootmicro.Controller;

import com.sha.springbootmicro.Dto.ProductDto;
import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Service.IService;
import com.sha.springbootmicro.Service.ProductService;
import com.sha.springbootmicro.Service.ServiceEnum;
import com.sha.springbootmicro.Service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping
public class ProductController {
    @Autowired
    private ProductService mainService;

    @Autowired
    private ServiceFactory serviceFactory;

    private JpaRepository<Product,Long> repository;

    public ProductController(JpaRepository<Product,Long> repository) {
        this.repository = repository;
    }

    @GetMapping("v1/product/{id}/")
    public ResponseEntity<Product> getProductByid(@PathVariable long id) {
        IService service = serviceFactory.getService(ServiceEnum.productService);
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping("v1/product/")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Validated Product product) {
        Product created_product = mainService.saveEntity(product);
        return new ResponseEntity<>(mainService.getProductDto(created_product.getId()), HttpStatus.CREATED);
    }

    @GetMapping("v1/getProductByIds/")
    public ResponseEntity<List<Optional<Product>>> getProductByIds(@RequestBody List<Long> ids) {
        return ResponseEntity.ok().body(mainService.filterByIds(ids));
    }

    @DeleteMapping("v1/deleteProductByIds/")
    public ResponseEntity<List<Product>> deleteProductByIds(@RequestBody List<Long> ids) {
        mainService.deleteProduct(ids);
        return ResponseEntity.ok().body(mainService.getAllProducts());
    }

    @GetMapping("v1/product/")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok().body(mainService.get_products_dto(mainService.getAllProducts()));
    }


//    //Todo burası refactor edilecek
//    @GetMapping("v1/get_all_products_with_params/")
//    public ResponseEntity<?> get_all_products_with_name(@RequestParam(value = "name", required = false) String name) {
//        return ResponseEntity.ok().body(mainService.find_all_products_with_name(name));
//    }

    //TODO bu işlem servis katlamanına alınacak
    @RequestMapping(value = "v1/product/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> partial_update(
            @RequestBody Map<String, Object> updates,
            @PathVariable("id") Long id) {
        Product product = mainService.findById(id);
        updates.remove("id");
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(product.getClass(), key);
            //field.setAccessible(true) private veya protected alana erişilebilir hale getirmek için kullanılır,
            field.setAccessible(true);
            //Reflection Utils, Spring Framework içinde sunulan bir sınıftır ve Java Reflection API'sini kullanarak
            // kolayca nesne özelliklerine ve metotlarına erişmenizi sağlar.
            ReflectionUtils.setField(field, product, value);
        });
        repository.save(product);
        return ResponseEntity.ok(mainService.getProductDto(id));
    }

}
