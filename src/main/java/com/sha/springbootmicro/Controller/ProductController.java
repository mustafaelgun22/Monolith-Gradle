package com.sha.springbootmicro.Controller;

import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Service.IService;
import com.sha.springbootmicro.Service.ServiceEnum;
import com.sha.springbootmicro.Service.ServiceFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping
public class ProductController {

    private IService service;

    private ServiceFactory serviceFactory;

    public ProductController(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
        this.service = serviceFactory.getService(ServiceEnum.productService);
    }

    @GetMapping("v1/product/{id}/")
    public ResponseEntity<?> getProductByid(@PathVariable long id) {

        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping("v1/product/")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product created_product = (Product) service.saveEntity(product);
        return new ResponseEntity<>(service.getDto(created_product.getId()), HttpStatus.CREATED);
    }

    @GetMapping("v1/getProductByIds/")
    public ResponseEntity<List<Optional<Product>>> getProductByIds(@RequestBody List<Long> ids) {
        return ResponseEntity.ok().body(service.filterByIds(ids));
    }

    @DeleteMapping("v1/deleteProductByIds/")
    public ResponseEntity<List<Product>> deleteProductByIds(@RequestBody List<Long> ids) {
        service.deleteObjects(ids);
        return ResponseEntity.ok().body(service.getAllObjects());
    }

    @DeleteMapping("v1/product/{id}/")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id){
        service.deleteObject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("v1/product/")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok().body(service.get_objects_dto(service.getAllObjects()));
    }


//    //Todo burası refactor edilecek
//    @GetMapping("v1/get_all_products_with_params/")
//    public ResponseEntity<?> get_all_products_with_name(@RequestParam(value = "name", required = false) String name) {
//        return ResponseEntity.ok().body(mainService.find_all_products_with_name(name));
//    }


    @RequestMapping(value = "v1/product/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> partial_update(
            @RequestBody Map<String, Object> updates,
            @PathVariable("id") Long id) {
        Optional<Product> product = service.findById(id);
        if (product == null) {
            ResponseEntity.noContent();
        }
        updates.remove("id");
        service.updateObject(updates, product);
        return ResponseEntity.ok(service.getDto(id));
    }

    @RequestMapping("v1/product/{id}/add_attributes/")
    public ResponseEntity<?> attributes(@PathVariable Long id,@RequestBody Map<String,Object> attributes) throws Throwable {
        IService service = serviceFactory.getService2(ServiceEnum.productService);
        Product product= (Product) service.findById(id).orElseThrow(()-> new IllegalArgumentException("product not found"));
        return ResponseEntity.ok().body(service.addAttributes(id, product, attributes));
    }

}
