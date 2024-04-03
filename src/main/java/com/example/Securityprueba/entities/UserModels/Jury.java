package com.example.Securityprueba.entities.UserModels;

import com.example.Securityprueba.entities.SecurityModels.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
@Entity
public class Jury extends Users {
    private  String GradeASigne;
    @Enumerated( value = EnumType.STRING)
    private Role role;

    public Jury() {
        super();
    }

    public String getGradeASigne() {
        return GradeASigne;
    }

    public void setGradeASigne(String gradeASigne) {
        GradeASigne = gradeASigne;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
