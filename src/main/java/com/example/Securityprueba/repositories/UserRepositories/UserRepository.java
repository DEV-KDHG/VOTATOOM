package com.example.Securityprueba.repositories.UserRepositories;

import com.example.Securityprueba.entities.UserModels.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepository  extends JpaRepository <Users, Long> {


    Optional<Users> findByUsername(String username);
}
