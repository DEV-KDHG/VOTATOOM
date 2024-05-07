package com.example.Securityprueba.repositories.CandidatesRepository;

import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ComptrollerRepository extends JpaRepository <Comptroller, Long> {


    Optional<Comptroller> findByGrade(Integer grade);
    Optional<Comptroller> findByName(String name );
    Optional<Comptroller> findByIdentification(Long identification);

    Optional<Comptroller> findById(Long id);


    List<Comptroller> findAll();

    void deleteByIdentification(Long identification);

    void delete(Comptroller comptroller);




}
