package com.sr.KT_ecommerce_spring_jwt_backend.Service;

import com.sr.KT_ecommerce_spring_jwt_backend.Entity.Users;
import com.sr.KT_ecommerce_spring_jwt_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;



    public ResponseEntity<Map<String,String>> saveUser(Users users){
        Map<String,String> status = new HashMap<>();
        if(userRepo.existsByEmail(users.getEmail())){
            status.put("status","unsuccessful");
            return ResponseEntity.badRequest().body(status);
        }
        status.put("status","successful");
        users.setPassword(new BCryptPasswordEncoder(12).encode(users.getPassword()));
        userRepo.save(users);
        return ResponseEntity.ok().body(status);

    }
}
