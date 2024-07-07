package com.satwik.ecommerce.repo;

import com.satwik.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.prodName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchProduct(String name);

}
