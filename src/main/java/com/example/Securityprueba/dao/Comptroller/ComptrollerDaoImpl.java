package com.example.Securityprueba.dao.Comptroller;

import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.repositories.CandidatesRepository.ComptrollerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class ComptrollerDaoImpl implements ComptrollerDao{


   private final ComptrollerRepository comptrollerRepository;

    public ComptrollerDaoImpl(ComptrollerRepository comptrollerRepository) {
        this.comptrollerRepository = comptrollerRepository;
    }




    @Override
    public Optional<Comptroller> findByGrade(Integer grade) {
        return comptrollerRepository.findByGrade(grade);
    }

    @Override
    public Optional<Comptroller> findByName(String name) {
        return comptrollerRepository.findByName(name);
    }

    @Override
    public Optional<Comptroller> findById(Long id) {
        return comptrollerRepository.findById(id);
    }

    @Override
    public List<Comptroller> findAll() {
        return comptrollerRepository.findAll();
    }

    @Override
    public void save(Comptroller comptroller) {
      comptrollerRepository.save(comptroller);
    }

    @Override
    public void deleteById(Long id) {
 comptrollerRepository.deleteById(id);
    }

    @Override
    public void delete(Comptroller comptroller) {
comptrollerRepository.delete(comptroller);
    }
}
