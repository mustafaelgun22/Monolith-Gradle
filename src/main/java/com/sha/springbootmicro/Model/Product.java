package com.sha.springbootmicro.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


//Bir sınıfın veri tabanında bir karşılığı olması için entity annotasyonunun olması gereklidir.

@Entity
@Table(name = "product")
@Data
@Getter
@Setter
public class Product {

    //TODO zorunluluktan bu constructor u koydum fakat tam olarak kafamda oturmadı.
    public Product() {
    }

    public Product(String name, double price, String type, Date date, List<Gallery> galleries) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.date = date;
        this.galleries = galleries;
    }
//@Lob anatasyonu veritabanında büyük veri tipi oldugunu belirtir.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false)
    private Long id;

    //version transactionlarda aynı veri üzerinde aynı anda değişiklik yapılmasını önler.

    @Column(name="name",length = 150,nullable = false)
    private String name;

    @Column(name="price",nullable = false)
    private double price;

    @Column(name="type",length = 150,nullable = false)
    private String type;

    //Temporal tarihi veritabanına hangi formatta yazılacağını belirtir.
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            targetEntity = Gallery.class
    )
    private List<Gallery> galleries;

    @PostPersist
    public void postPersist() {
        System.out.println(String.format("%s product is created", this.name));

    }

    private Product(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.type = builder.type;
    }

    public static class Builder{
        private Long id;
        private String name;
        private Double price;
        private String type;

        public Builder(Long id,String name, Double price, String type) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.type = type;
        }
        public Product build(){
            return new Product(this);
        }
    }
}
