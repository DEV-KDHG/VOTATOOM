package com.example.Securityprueba.entities;

import lombok.Getter;

import java.security.Principal;


    public class CustomPrincipal implements Principal {
        private final String name;
        @Getter
        private final Long identification;

        public CustomPrincipal(String name, Long identification) {
            this.name = name;
            this.identification = identification;
        }

        @Override
        public String getName() {
            return name;
        }

    }
