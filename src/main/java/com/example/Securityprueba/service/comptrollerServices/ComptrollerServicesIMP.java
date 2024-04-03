package com.example.Securityprueba.service.comptrollerServices;

import com.example.Securityprueba.dao.Comptroller.ComptrollerDaoImpl;
import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ComptrollerServicesIMP implements ComptrollerService {

    private  final ComptrollerDaoImpl comptrollerDao;

    public ComptrollerServicesIMP(ComptrollerDaoImpl comptrollerDao) {
        this.comptrollerDao = comptrollerDao;
    }


    @Override
    public Optional<Comptroller> findByGrade(Integer grade) {
        return comptrollerDao.findByGrade(grade);
    }

    @Override
    public Optional<Comptroller> findByName(String name) {
        return comptrollerDao.findByName(name);
    }

    @Override
    public Optional<Comptroller> findById(Long id) {
        return comptrollerDao.findById(id);
    }

    @Override
    public List<Comptroller> findAll() {
        return comptrollerDao.findAll();
    }

    @Override
    public void save(Comptroller comptroller) {
comptrollerDao.save(comptroller);
    }

    @Override
    public void deleteById(Long id) {
comptrollerDao.deleteById(id);
    }

    @Override
    public void delete(Comptroller comptroller) {
comptrollerDao.delete(comptroller);
    }
}
