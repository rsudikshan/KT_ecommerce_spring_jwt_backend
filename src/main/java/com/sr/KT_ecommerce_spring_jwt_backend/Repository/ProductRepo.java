package com.sr.KT_ecommerce_spring_jwt_backend.Repository;

import com.sr.KT_ecommerce_spring_jwt_backend.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {

    public Products findByProductName(String name);

}
