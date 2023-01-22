package com.sha.springbootmicro.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {
    //TODO BURASI FARKLI VERİTABANLARINI KULLANMALI
    @Autowired
    private MockMvc mockMvc;

    //Object Mapper, Java objelerini ve diğer veri türlerini
    // (örneğin JSON, XML, CSV) arasında serileştirmek ve deserileştirmek için kullanılan bir araçtır.
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product.Builder(10L,"Test Product",9.99,"deneme").build();

        mockMvc.perform(post("/v1/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetProduct() throws Exception {
        Product product = new Product.Builder(10L,"Test Product",9.99,"deneme").build();
        product = productRepository.save(product);

        mockMvc.perform(get("/v1/product/{id}/", product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.price").value(9.99));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product.Builder(10L,"Test Product",9.99,"deneme").build();
        product = productRepository.save(product);

        product.setName("Updated Product");
        product.setPrice(19.99);

        mockMvc.perform(patch("/v1/product/{id}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/v1/product/{id}/", product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.price").value(19.99));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = new Product.Builder(10L,"Test Product",9.99,"deneme").build();
        product = productRepository.save(product);

        mockMvc.perform(delete("/v1/product/{id}/", product.getId()))
                .andExpect(status().isNoContent());
    }
}
