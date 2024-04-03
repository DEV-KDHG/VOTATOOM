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
    @Transactional(readOnly = true)
    @Override
    public Optional<Personero> findByGrade(Integer grade) {
        return personeroDao.findByGrade(grade) ;
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Personero> findByName(String name) {
        return  personeroDao.findByName(name);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Personero> findAll() {
        return personeroDao.findAll();
    }
    @Transactional()
    @Override
    public void deleteById(long id) {
personeroDao.deleteById(id);
    }
}
