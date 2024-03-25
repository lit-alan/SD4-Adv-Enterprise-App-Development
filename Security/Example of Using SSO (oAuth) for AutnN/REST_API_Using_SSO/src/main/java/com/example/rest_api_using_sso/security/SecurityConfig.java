package com.example.rest_api_using_sso.security;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /** The CSRF protection is often disabled for REST APIs where the authentication is token-based,
         * not cookie-based, as CSRF attacks typically involve maliciously exploiting the cookies stored by a browser session.
         **/

        http.csrf(AbstractHttpConfigurer::disable) //disables Cross-Site Request Forgery (CSRF) protection for the application. In this application it is not needed and can be disabled.
                .authorizeHttpRequests(authorize -> authorize //begins the configuration of URL-based authorization.
                        .requestMatchers("/", "goodbye").permitAll() // Specifies that requests to the root URL ("/") and "/goodbye" are allowed without authentication. This means anyone can access these routes.
                        .anyRequest().authenticated()) // Any other request not matched by the above rule must be authenticated. This means they must provide valid login credentials.
                .oauth2Login();  // Configure OAuth 2.0 login. If a request requires authentication, Spring Security will redirect to the configured OAuth 2.0 provider (GitHub in the case of this example) for login.

        // After all the configurations are set up, build the SecurityFilterChain object and return it. This object will be used by Spring Security to determine the security settings for the application.
        return http.build();
    }
}
