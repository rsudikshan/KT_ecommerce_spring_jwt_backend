package com.sr.KT_ecommerce_spring_jwt_backend.Filter;

import com.sr.KT_ecommerce_spring_jwt_backend.Service.JwtService;
import com.sr.KT_ecommerce_spring_jwt_backend.Service.KtUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService service;

    @Autowired
    KtUserDetailsService userDetailsService;



    @Override
    protected void doFilterInternal
            (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {

        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);
            String username = service.getUsername(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (service.validateToken(userDetails,token)) {

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken
                                    (userDetails,null,userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }

            }
        }
        filterChain.doFilter(request,response);

    }
}