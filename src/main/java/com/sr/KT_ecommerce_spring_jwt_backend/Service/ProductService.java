package com.sr.KT_ecommerce_spring_jwt_backend.Service;

import com.sr.KT_ecommerce_spring_jwt_backend.Entity.Products;
import com.sr.KT_ecommerce_spring_jwt_backend.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public ResponseEntity<Map<String,String>> addProducts(Products products){
        Map<String,String> response = new HashMap<>();

        if(repo.findByProductName(products.getProductName()) == null){
            repo.save(products);
            response.put("status","successful");
            response.put("product",products.getProductName());

            return ResponseEntity.ok().body(response);
        }
        else {
            response.put("status","unsuccessful");
            return ResponseEntity.badRequest().body(response);

        }
    }

    public List<Products> getProducts(){
        return repo.findAll();

    }
}
