package com.example.Securityprueba.entities.UserModels;

import com.example.Securityprueba.entities.SecurityModels.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;



@Entity
@Data
@Table(name = "administrador")
public class Administrators extends Users {
    private String nameIns;

    @Enumerated( value = EnumType.STRING)
    private Role role;


    public Administrators(String username, String password, String name, String lastName, String nameIns, Role role) {
        super(username, password, name, lastName);
        this.nameIns = this.nameIns;
        this.role = role;
    }

    public Administrators() {
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


    public String getNameIns(String nameIns) {
        return this.nameIns;
    }

}
