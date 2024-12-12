package com.sr.KT_ecommerce_spring_jwt_backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{

        security.httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .sessionManagement(
                        customizer ->
                                customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf( customizer -> customizer.disable())
                .authorizeHttpRequests( request->
                        request.requestMatchers("/auth/**").permitAll().anyRequest().authenticated());



        return security.build();
    }

}
