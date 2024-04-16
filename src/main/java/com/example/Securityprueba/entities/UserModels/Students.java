package com.example.Securityprueba.entities.UserModels;

import com.example.Securityprueba.entities.SecurityModels.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Data
@Entity
public class Students extends Users {
    private Integer grade;

    @Column(unique = true)
    private String code;

    @Column(name = "`group`")
    private String group;
@Column(name = "state_votation")
    private  Boolean stateVotation=false;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    // Método ejecutado antes de persistir la entidad por primera vez
    @PrePersist
    public void generateUniqueCode() {
        // Longitud deseada del código
        int length = 5;

        // Caracteres permitidos para el código (letras mayúsculas y dígitos)
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // StringBuilder para construir el código
        StringBuilder sb = new StringBuilder();

        // Instancia de Random para generar números aleatorios
        Random random = new Random();

        // Generar el código con la longitud especificada
        for (int i = 0; i < length; i++) {
            // Obtener un índice aleatorio dentro del rango de caracteres
            int index = random.nextInt(characters.length());

            // Agregar el carácter correspondiente al código
            sb.append(characters.charAt(index));
        }

        // Asignar el código generado al campo 'code'
        this.code = sb.toString();
    }
    public String getCode() {
        return code;
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



