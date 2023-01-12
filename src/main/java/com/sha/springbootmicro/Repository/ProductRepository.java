package com.sha.springbootmicro.Repository;


import com.sha.springbootmicro.Model.Product;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
