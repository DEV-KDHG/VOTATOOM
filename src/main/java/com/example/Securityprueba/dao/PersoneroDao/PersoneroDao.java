package com.example.Securityprueba.dao.PersoneroDao;

import com.example.Securityprueba.entities.candidatesModels.Personero;

import java.util.List;
import java.util.Optional;

public interface PersoneroDao {
void save (Personero  personero);

    Optional<Personero> findByGrade(Integer grade);
    List<Personero> findByName(String name);
    Optional<Personero>findById(Long id);

    Optional<Personero>findByIdentification(Long identification);
    List<Personero>findAllByGrade(int grade);

    List<Personero>findAll();

    void deleteById(long id);


}
