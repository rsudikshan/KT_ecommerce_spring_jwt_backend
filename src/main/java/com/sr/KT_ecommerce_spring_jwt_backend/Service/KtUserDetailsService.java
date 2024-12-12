package com.sr.KT_ecommerce_spring_jwt_backend.Service;

import com.sr.KT_ecommerce_spring_jwt_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class KtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepo.findByUsername(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("Username not found");
        }
        return userDetails;
    }
}
