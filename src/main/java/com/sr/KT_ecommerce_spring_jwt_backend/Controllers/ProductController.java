package com.sr.KT_ecommerce_spring_jwt_backend.Controllers;

import com.sr.KT_ecommerce_spring_jwt_backend.Entity.Products;
import com.sr.KT_ecommerce_spring_jwt_backend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("/add")
    public ResponseEntity<Map<String,String>> addProducts(@RequestBody Products product){

        return service.addProducts(product);

    }

    @GetMapping("/getAllProducts")
    public List<Products> getProducts(){
        return service.getProducts();
    }
}
