package com.example.Securityprueba.repositories.UserRepositories;

import com.example.Securityprueba.entities.UserModels.Jury;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import org.apache.catalina.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface UserRepository  extends JpaRepository <Users, Long> {
    boolean existsByUsername(String username);
boolean existsByIdentification(Long identification);
    Optional<Students> findStudentByUsername(String username);

    Optional<Users> findByUsername(String username);
Optional<Users> findById(Long id);
    void deleteById(Long id);
    void deleteByIdentification(Long identification);
    List<Users> findAllByName(String name);
    Optional<Users> findUserByIdentification(Long identification); // Cambiado el nombre del método para usuarios

    Optional<Students> findStudentByIdentification(Long identification);

    Optional<Students>findByName(String name);
     Optional<Jury> findJuryByIdentification(Long identification);
List<Users>findAll();
  List<Students> findAllByGrade(Integer grade);
  List<Students>findStudentByStateVotationTrue();


}
