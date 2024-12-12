package com.sr.KT_ecommerce_spring_jwt_backend.Config;

import com.sr.KT_ecommerce_spring_jwt_backend.Service.KtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    KtUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{

        security.httpBasic(Customizer.withDefaults())
                .sessionManagement(
                        customizer ->
                                customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf( customizer -> customizer.disable())
                .authorizeHttpRequests( request->
                        request.requestMatchers("/auth/**").permitAll().anyRequest().authenticated());



        return security.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }
}
