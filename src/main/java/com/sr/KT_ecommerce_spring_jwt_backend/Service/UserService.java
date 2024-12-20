package com.sr.KT_ecommerce_spring_jwt_backend.Service;

import com.sr.KT_ecommerce_spring_jwt_backend.Entity.Users;
import com.sr.KT_ecommerce_spring_jwt_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    JwtService service;

    @Autowired
    AuthenticationManager manager;



    public ResponseEntity<Map<String,String>> login(Users user){

        Map<String,String> map = new HashMap<>();


        Authentication authentication = manager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );

        if(authentication.isAuthenticated()){
            map.put("status","successful");
            map.put("bearer ", service.createJwt(user.getUsername()));
            return ResponseEntity.ok().body(map);
        }
        else {
            map.put("status","unsuccessful");
            return ResponseEntity.badRequest().body(map);
        }
    }



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

    public Users loadByUsername(String username){

        return userRepo.findByUsername(username);
    }
}
