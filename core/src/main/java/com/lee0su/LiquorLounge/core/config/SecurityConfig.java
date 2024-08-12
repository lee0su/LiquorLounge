package com.lee0su.LiquorLounge.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/guest/**","/member/**", "/api/users/register","/api/users/check-username","/api/users/signIn", "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/guest/signIn") // 내(커스텀) 로그인 페이지
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/api/users/logout") // 로그아웃 URL
                        .logoutSuccessUrl("/guest/main") // 로그아웃 성공 후 리다이렉트
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
