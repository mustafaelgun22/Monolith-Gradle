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
}
