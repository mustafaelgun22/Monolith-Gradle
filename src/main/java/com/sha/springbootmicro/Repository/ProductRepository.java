package com.sha.springbootmicro.Repository;


import com.sha.springbootmicro.Model.Product;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM Product")
    public List<Product> find_all_products();

    @Query(nativeQuery = true,value = "SELECT * FROM Product p WHERE p.name ilike %?1%")
    public List<Product> find_all_products_with_name(String name);
}
