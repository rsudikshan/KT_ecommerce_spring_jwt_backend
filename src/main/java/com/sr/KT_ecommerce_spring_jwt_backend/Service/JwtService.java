package com.sr.KT_ecommerce_spring_jwt_backend.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
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

        Map<String, Object> claims = new HashMap<>();


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*30))
                .signWith(getKey())
                .compact();



    }

    public SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(key);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsername(String token){
        //logger.debug(extractClaims(token,Claims::getSubject) + " " + extractClaims(token,Claims::getExpiration));
        return extractClaims(token,Claims::getSubject);
    }

    public Date getExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    public Boolean isExpired(String token){

        return getExpiration(token).before(new Date());
    }


    public boolean validateToken(UserDetails userDetails, String token) {
        String username = getUsername(token);

        if (isExpired(token)) {
            return false;
        }
        return username.equals(userDetails.getUsername());
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimResolver){
        final Claims claims = parseJwtAndExtractAllClaims(token);

        return claimResolver.apply(claims);
    }


    public Claims parseJwtAndExtractAllClaims(String token){

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
