package com.example.Securityprueba.repositories.UserRepositories;

import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface UserRepository  extends JpaRepository <Users, Long> {


    Optional<Users> findByUsername(String username);
Optional<Users> findById(Long id);
    void deleteById(Long id);
    List<Users> findAllByName(String name);
    Optional<Users> findUserByIdentification(Long identification); // Cambiado el nombre del método para usuarios

    Optional<Students> findStudentByIdentification(Long identification);
    Optional<Students>findByName(String name);


  List<Students> findAllByGrade(Integer grade);
}
