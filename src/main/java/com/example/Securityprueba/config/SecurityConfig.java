package com.example.Securityprueba.config;


import com.example.Securityprueba.filter.JwtAuthenticationFilter;
import com.example.Securityprueba.service.UserDetailsServiceImp;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticattionFilter1;
private final UserDetailsServiceImp userDetailsServiceImp;


    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticattionFilter1, UserDetailsServiceImp userDetailsServiceImp) {
        this.jwtAuthenticattionFilter1 = jwtAuthenticattionFilter1;
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(String.valueOf(PathRequest.toStaticResources().atCommonLocations())).permitAll()
                        .requestMatchers("/register/**",  "/login/**", "/register/jury").permitAll()
                        .requestMatchers("/hello").hasAuthority("STUDENT")
                        .requestMatchers("/admin/hello").hasAuthority("ADMIN")
                        .requestMatchers("/jury/hello").hasAuthority("JURY")
                        .anyRequest().authenticated())
                .userDetailsService(userDetailsServiceImp)

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(jwtAuthenticattionFilter1, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}