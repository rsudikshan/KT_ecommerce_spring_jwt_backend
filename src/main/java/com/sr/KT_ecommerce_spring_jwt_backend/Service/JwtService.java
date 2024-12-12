package com.sr.KT_ecommerce_spring_jwt_backend.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    public String key;


    JwtService(){
        try {
            KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = generator.generateKey();
            key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException();
        }

    }

    public String createJwt(String username){

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*30))
                .signWith(getKey())
                .compact();



    }

    public Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(key);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
