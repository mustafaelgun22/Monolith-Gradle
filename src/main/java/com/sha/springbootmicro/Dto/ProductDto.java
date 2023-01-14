package com.sha.springbootmicro.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String name;

    private double price;

    public ProductDto(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
