package com.JWTTest.MyConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.JWTTest.MySecurity.JwtAuthenticationEntryPoint;
import com.JWTTest.MySecurity.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    
    
    @Bean
   	  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/login", "/auth/test", "/mytest", "/customlogin").permitAll();
                    auth.requestMatchers("/home/**").authenticated();

                    auth.anyRequest().authenticated();

                })
                .csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
                // .httpBasic(Customizer.withDefaults())
                .formLogin(login -> login.loginPage("/customlogin").permitAll());
   		
   		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
   		http.exceptionHandling(ex -> ex.authenticationEntryPoint(point));
   		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

   		return http.build();
   	}
    
    
    



}