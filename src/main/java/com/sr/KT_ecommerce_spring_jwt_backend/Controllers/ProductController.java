package com.sr.KT_ecommerce_spring_jwt_backend.Controllers;

import com.sr.KT_ecommerce_spring_jwt_backend.Entity.Product;
import com.sr.KT_ecommerce_spring_jwt_backend.Service.ProductService;
import com.sr.KT_ecommerce_spring_jwt_backend.dto.ProductRequest;
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
    public ResponseEntity<Map<String,String>> addProducts(@RequestBody ProductRequest product){
        return service.addProducts(product);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/getProductsCategorically")
    public ResponseEntity<List<ProductRequest>> getCategorically(@RequestBody ProductRequest request){
        return  service.getCategorically(request);
    }

    @GetMapping("/searchProduct")
    public ResponseEntity<List<String>> searchProduct(@RequestBody ProductRequest request){
        return service.findProduct(request);
    }



}
