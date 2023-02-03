package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import static com.sha.springbootmicro.Service.ServiceTestSupport.generate_product;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


    private ProductService productService;
    private Product product;
    private ProductRepository productRepository;
    @BeforeEach
    void setUp() {
        this.product = Mockito.mock(Product.class);
        this.productRepository = Mockito.mock(ProductRepository.class);
        this.productService= new ProductService(productRepository);

    }

    @Test
    void findbyid() {
        long id = 10;
        when(productRepository.findById(id)).thenReturn(Optional.of(generate_product(id)));
        Product product = productService.findById(id).orElseThrow(()-> new IllegalArgumentException("Product not found"));
        Assertions.assertEquals(product.getId(),generate_product(id).getId());
    }

    @Test
    void saveProduct() {
        Mockito.when(productService.saveEntity(ArgumentMatchers.any(Product.class))).thenReturn(product);
        Assertions.assertEquals(productService.saveEntity(product).getPrice(),product.getPrice());
    }


    @Test
    void filterbyids() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        List<Product> products = new ArrayList<>();
        ids.forEach(id -> products.add(
                new Product.Builder(id, "Product 1", 10.0, "Description 1").build()));

        ids.forEach(id -> when(productRepository.findById(id)).thenReturn(
                products.stream().filter(p -> p.getId().
                                equals(id)).findFirst()));

        List<Optional<Product>> final_products = productService.filterByIds(ids);
        Assertions.assertEquals(3, final_products.size());
        for(Optional<Product> product : final_products) {
            Assertions.assertTrue(product.isPresent());
        }
    }

    @Test
    void deleteProduct() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        productService.deleteObjects(ids);
        //bir kere çağırılmış olmasını beklemekteyim.
        for(Long id:ids) {
            verify(productRepository, times(1)).deleteById(id);
        }
    }

    @Test
    void get_all_products() {
        List<Product> products = new ArrayList<>();
        LongStream.range(1L, 4L).forEach(i -> {
            products.add(
                    new Product.Builder(i, "Product 1", 10.0, "Description 1").build());
        });
        when(productRepository.findAll()).thenReturn(products);
        Assertions.assertEquals(productService.getAllObjects(),products);
    }

    @Test
    void get_product_dto() {
    }

    @Test
    void get_products_dto() {
    }
}