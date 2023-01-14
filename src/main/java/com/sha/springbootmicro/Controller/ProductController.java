package com.sha.springbootmicro.Controller;

import com.sha.springbootmicro.Dto.ProductDto;
import com.sha.springbootmicro.Exception.ProductNotFoundException;
import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Repository.ProductRepository;
import com.sha.springbootmicro.Service.IProductservice;
import com.sha.springbootmicro.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping
public class ProductController {
    @Autowired
    private ProductService mainService;

    @GetMapping("v1/product/{id}/")
    public ResponseEntity<Optional<Product>> getProductByid(@PathVariable long id){
        return ResponseEntity.ok(mainService.findbyid(id));
    }

    @PostMapping("v1/product/")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Validated Product product){
        Product created_product=mainService.saveProduct(product);
        ProductDto dto = new ProductDto(created_product.getName(), created_product.getPrice());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("v1/product_filters/")
    public ResponseEntity<List<Optional<Product>>> getfilteredproducts(@RequestBody List<Long> ids){
        return ResponseEntity.ok().body(mainService.filterbyids(ids));
    }

    @DeleteMapping("v1/delete/")
    public ResponseEntity<?> deleteProducts(@RequestBody List<Long> ids){
        mainService.deleteProduct(ids);
        return ResponseEntity.ok().body(mainService.get_all_products());
    }

    @GetMapping("v1/get_all_products")
    public ResponseEntity<?> get_all_products(){
        return ResponseEntity.ok().body(mainService.find_all_products());
    }


    @GetMapping("v1/get_all_products_with_params/")
    public ResponseEntity<?> get_all_products_with_name(@RequestParam(value = "name", required = false) String name){
        return ResponseEntity.ok().body(mainService.find_all_products_with_name(name));
    }
}
