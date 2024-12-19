package com.sr.KT_ecommerce_spring_jwt_backend.Repository;

import com.sr.KT_ecommerce_spring_jwt_backend.Entity.Product;
import com.sr.KT_ecommerce_spring_jwt_backend.dto.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    public List<Product> findByProductName(String name);
    public List<Product> findByCategory(String category);

}
