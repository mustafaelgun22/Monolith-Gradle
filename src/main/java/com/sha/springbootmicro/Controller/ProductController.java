package com.sha.springbootmicro.Controller;

import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Repository.ProductRepository;
import com.sha.springbootmicro.Service.IProductservice;
import com.sha.springbootmicro.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping
public class ProductController {
    @Autowired
    private IProductservice productService;
    @Autowired
    private ProductService mainService;

    @GetMapping("v1/product/{id}/")
    public ResponseEntity<Optional<Product>> getProductByid(@PathVariable long id){
        Optional<Product> product = productService.findbyid(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("v1/product/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product responseProduct = productService.saveProduct(product);
        return ResponseEntity.ok().body(responseProduct);
    }

    @GetMapping("v1/product_filters/")
    public ResponseEntity<List<Optional<Product>>> getfilteredproducts(@RequestBody List<Long> ids){
        List<Optional<Product>> products = mainService.filterbyids(ids);
        return ResponseEntity.ok().body(products);
    }

    @DeleteMapping("v1/delete/")
    public ResponseEntity<?> deleteProducts(@RequestBody List<Long> ids){
        mainService.deleteProduct(ids);
        List<?> products = mainService.get_all_products();
        return ResponseEntity.ok().body(products);
    }

}
