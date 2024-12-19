package com.sr.KT_ecommerce_spring_jwt_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    public String productName;
    public String productPrice;
    public String category;
    public String imageName;
}
