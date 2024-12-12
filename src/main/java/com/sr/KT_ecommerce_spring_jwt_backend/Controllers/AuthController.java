package com.sr.KT_ecommerce_spring_jwt_backend.Controllers;

import com.sr.KT_ecommerce_spring_jwt_backend.Entity.Users;
import com.sr.KT_ecommerce_spring_jwt_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService service;


    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Users users){
        return service.saveUser(users);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users){
       return service.login(users);
    }
}
