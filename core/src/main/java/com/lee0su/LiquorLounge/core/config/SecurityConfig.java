package com.lee0su.LiquorLounge.core.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/guest/**","/member/**", "/pages/**", "/component/**", "/api/users/register","/api/users/check-session","/api/users/check-username","/api/users/sign-in", "/api/liquors/**", "/api/session/username", "/css/**", "/js/**", "/images/**", "/font/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/pages/sign-in") // 내(커스텀) 로그인 페이지
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/api/users/logout") // 로그아웃 URL
                        .logoutSuccessHandler((HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
                            request.getSession().invalidate();
                            response.sendRedirect("/pages/main");
                        })
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
