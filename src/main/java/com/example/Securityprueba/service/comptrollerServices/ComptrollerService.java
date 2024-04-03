package com.example.Securityprueba.service.comptrollerServices;

import com.example.Securityprueba.entities.candidatesModels.Comptroller;

import java.util.List;
import java.util.Optional;

public interface ComptrollerService {

    Optional<Comptroller> findByGrade(Integer grade);
    Optional<Comptroller> findByName(String name );

    Optional<Comptroller> findById(Long id);


    List<Comptroller> findAll();

    void save (Comptroller comptroller);
    void deleteById(Long id);

    void delete(Comptroller comptroller);
}
