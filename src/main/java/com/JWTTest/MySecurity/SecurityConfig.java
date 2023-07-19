package com.JWTTest.MySecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    
    
    @Bean
   	public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
   		http

   				.authorizeHttpRequests(auth -> {
   					auth.requestMatchers("/auth/login").permitAll();
   					auth.requestMatchers("/home/**").authenticated();
   					
   					auth.anyRequest().authenticated();

   				})
   				.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
   				// .httpBasic(Customizer.withDefaults())
   				.formLogin(Customizer.withDefaults());
   		
   		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
   		http.exceptionHandling(ex -> ex.authenticationEntryPoint(point));
   		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

   		return http.build();
   	}
    
    
    



}