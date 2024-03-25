package com.sample.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) //disables Cross-Site Request Forgery (CSRF) protection for the application. As this application used token-based authentication, CSRF protection is not needed and can be disabled.
                .authorizeHttpRequests(authorize -> authorize //begins the configuration of URL-based authorization.
                        .requestMatchers("/authenticate", "/goodbye").permitAll() //specifies that any requests to /authenticate and /goodbye URLs do not require authentication, meaning they are publicly accessible.
                        .anyRequest().authenticated()) //any request that does not match the previously defined matchers must be authenticated
                .sessionManagement(sessionManagement -> sessionManagement //begins the configuration for session management in the application.
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //specifies that the server will not create a HTTP session. This means that the server will not hold any session state for the client. This is typically used in REST APIs and applications that use token-based authentication (such as this one), ensuring that the authentication state is not tied to a session that the server manages.


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
