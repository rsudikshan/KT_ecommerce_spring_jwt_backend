package com.sr.KT_ecommerce_spring_jwt_backend.Service;

import com.sr.KT_ecommerce_spring_jwt_backend.Entity.Product;
import com.sr.KT_ecommerce_spring_jwt_backend.Repository.ProductRepo;
import com.sr.KT_ecommerce_spring_jwt_backend.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public ResponseEntity<Map<String,String>> addProducts(ProductRequest productRequest){
        Map<String,String> response = new HashMap<>();

        if(repo.findByProductName(productRequest.getProductName()) == null){
            Product product = Product.builder()
                            .productName(productRequest.getProductName())
                            .productPrice(productRequest.getProductPrice())
                            .category(productRequest.getCategory())
                            .build();


            repo.save(product);
            response.put("status","successful");
            response.put("product",product.getProductName());

            return ResponseEntity.ok().body(response);
        }
        else {
            response.put("status","unsuccessful");
            return ResponseEntity.badRequest().body(response);

        }
    }

    public List<Product> getProducts(){
        return repo.findAll();

    }

    public ResponseEntity<List<ProductRequest>> getCategorically(ProductRequest request) {

        List<Product> requestList = repo.findByCategory(request.getCategory());
        List<ProductRequest> productRequests = requestList
                .stream()
                .map(this::mapToProductRequest)
                .toList();




        return ResponseEntity.ok().body(productRequests);

    }


    public ResponseEntity<List<String>> findProduct(ProductRequest request){

        List<Product> result = repo.findByProductName(request.getProductName());
        List<String> requestList = Collections.singletonList(result
                .stream()
                .map(Product::getProductName)
                .findFirst()
                .orElse("No specified product found"));


        return ResponseEntity.ok().body(requestList);

    }


    private ProductRequest mapToProductRequest(Product product){
        ProductRequest dto = new ProductRequest();
        dto.setCategory(product.category);
        dto.setProductName(product.productName);
        dto.setProductPrice(product.productPrice);

        return dto;

    }



}
