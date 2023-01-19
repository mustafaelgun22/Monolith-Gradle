package com.sha.springbootmicro.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name="Gallery")
public class Gallery {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name",length = 150,nullable = false)
    private String name;
    @ManyToMany
    private List<Product> products;
}
