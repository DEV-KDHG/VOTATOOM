package com.example.Securityprueba.dao.PersoneroDao;

import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.repositories.CandidatesRepository.PersoneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersoneroDaoImpl implements  PersoneroDao{
    @Autowired
    private PersoneroRepository personeroRepository;

    @Override
    public void save(Personero personero) {
        personeroRepository.save(personero);
    }

    @Override
    public Optional<Personero> findByGrade(Integer grade) {
        return personeroRepository.findByGrade(grade);
    }

    @Override
    public Optional<Personero> findByName(String name) {
        return personeroRepository.findByName(name);
    }

    @Override
    public List<Personero> findAll() {
        return personeroRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        personeroRepository.deleteById(id);
    }
}
