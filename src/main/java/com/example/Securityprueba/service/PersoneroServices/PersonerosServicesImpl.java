package com.example.Securityprueba.service.PersoneroServices;

import com.example.Securityprueba.dao.PersoneroDao.PersoneroDaoImpl;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonerosServicesImpl implements  PersonerosServices {
    @Autowired
    private PersoneroDaoImpl personeroDao;

@Transactional()
    @Override
    public void save(Personero personero) {
personeroDao.save(personero);
    }

    @Override
    public Optional<Personero> findByGrade(Integer grade) {
        return personeroDao.findByGrade(grade);
    }

    @Override
    public List<Personero> findByName(String name) {
        return personeroDao.findByName(name);
    }

    @Override
    public List<Personero> findAllByGrade(int grade) {
        return personeroDao.findAllByGrade(grade);
    }




    @Override
    public Optional<Personero> findByIdentification(Long identification) {
        return personeroDao.findByIdentification(
                identification
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<Personero> findAll() {
        return personeroDao.findAll();
    }

    @Override
    public Optional<Personero> findById(Long id) {
        return personeroDao.findById(id);
    }

    @Transactional()
    @Override
    public void deleteById(long id) {
personeroDao.deleteById(id);
    }
}
