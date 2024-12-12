package com.sr.KT_ecommerce_spring_jwt_backend.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/demo")
    public String testDemo(){
        return "Hello";
    }
}
