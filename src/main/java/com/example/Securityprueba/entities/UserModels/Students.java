package com.example.Securityprueba.entities.UserModels;

import com.example.Securityprueba.entities.SecurityModels.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;


    @Data
    @Entity


    public class Students extends Users {
        private String grade;

        private Long identification;
        private Long code;

        @Enumerated(value = EnumType.STRING)
        private Role role;



        public Students() {
            super();


        }



        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(role.name())
            );
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }



