package com.sha.springbootmicro.Controller;

import com.sha.springbootmicro.Dto.ProductDto;
import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Service.IService;
import com.sha.springbootmicro.Service.ProductService;
import com.sha.springbootmicro.Service.ServiceEnum;
import com.sha.springbootmicro.Service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("v1/product/{id}/")
    public ResponseEntity<?> getProductByid(@PathVariable long id) {
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
    public ResponseEntity<?> partial_update(
            @RequestBody Map<String, Object> updates,
            @PathVariable("id") Long id) {
        Optional<Product> product = mainService.findById(id);
        if (product == null) {
            ResponseEntity.noContent();
        }
        updates.remove("id");
        mainService.updateProduct(updates, product);
        return ResponseEntity.ok(mainService.getProductDto(id));
    }

}
