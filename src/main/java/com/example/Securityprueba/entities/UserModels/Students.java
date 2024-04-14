package com.example.Securityprueba.entities.UserModels;

import com.example.Securityprueba.entities.SecurityModels.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Students extends Users {
    private Integer grade;

    @Column(unique = true)
    private String code;

    @Column(name = "`group`")
    private String group;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    // Método ejecutado antes de persistir la entidad por primera vez
    @PrePersist
    public void generateUniqueCode() {
        this.code = UUID.randomUUID().toString();
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



