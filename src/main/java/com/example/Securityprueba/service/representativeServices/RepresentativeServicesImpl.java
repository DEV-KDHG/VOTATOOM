package com.example.Securityprueba.service.representativeServices;

import com.example.Securityprueba.dao.RepresentativeDao.RepresentativeDao;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepresentativeServicesImpl implements RepresentativeServices {

    @Autowired
    private RepresentativeDao representativeDao;

    @Transactional()
    @Override
    public void save(Representative representative) {
        representativeDao.save(representative);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public List<Representative> findAll() {
        return representativeDao.findAll();
    }

    @Transactional
    @Override
    public Optional<Representative> findByGrade(Integer grade) {
        return representativeDao.findByGrade(grade);
    }

    @Transactional
    @Override
    public Optional<Representative> findByIdentification(Long identificacion){
        return representativeDao.findByIdentification(identificacion);
    }

    @Transactional
    @Override
    public List<Representative> findByName(String name) {
        return representativeDao.findByName(name);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        representativeDao.deleteById(id);
    }
}
