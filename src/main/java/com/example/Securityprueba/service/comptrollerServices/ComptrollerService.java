package com.example.Securityprueba.service.comptrollerServices;

import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.entities.candidatesModels.Representative;

import java.util.List;
import java.util.Optional;

public interface ComptrollerService {

    Optional<Comptroller> findByGrade(Integer grade);
    Optional<Comptroller> findByName(String name );

    Optional<Comptroller> findById(Long id);

    Optional<Comptroller> findByIdentification(Long identification);
    List<Comptroller> findAll();

    void save (Comptroller comptroller);
    void deleteByIdentification(Long identification);

    void delete(Comptroller comptroller);
}
