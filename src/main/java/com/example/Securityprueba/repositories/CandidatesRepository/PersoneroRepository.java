package com.example.Securityprueba.repositories.CandidatesRepository;

import com.example.Securityprueba.entities.candidatesModels.Personero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersoneroRepository  extends JpaRepository<Personero,Long> {

    Optional<Personero> findByGrade(Integer grade);
    Optional<Personero>findByName(String name);

}
