package com.example.Securityprueba.service.PersoneroServices;

import com.example.Securityprueba.entities.candidatesModels.Personero;

import java.util.List;
import java.util.Optional;

public interface PersonerosServices {
    void save (Personero personero);
    Optional<Personero> findByGrade(Integer grade);
    Optional<Personero>findByName(String name);

    List<Personero> findAll();

    void deleteById(long id);
}
