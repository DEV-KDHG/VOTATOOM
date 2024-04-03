package com.example.Securityprueba.dao.PersoneroDao;

import com.example.Securityprueba.entities.candidatesModels.Personero;

import java.util.List;
import java.util.Optional;

public interface PersoneroDao {
void save (Personero  personero);
    Optional<Personero> findByGrade(Integer grade);
    Optional<Personero>findByName(String name);

    List<Personero>findAll();

    void deleteById(long id);


}
