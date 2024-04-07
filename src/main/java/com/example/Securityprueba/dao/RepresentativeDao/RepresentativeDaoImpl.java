package com.example.Securityprueba.dao.RepresentativeDao;

import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.repositories.CandidatesRepository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RepresentativeDaoImpl implements RepresentativeDao{

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Override
    public void save(Representative representative) {
        representativeRepository.save(representative);
    }

    @Override
    public List<Representative> findAll() {
        return representativeRepository.findAll();
    }

    @Override
    public Optional<Representative> findByIdentification(Long identification){
        return representativeRepository.findByIdentification(identification);
    }

    @Override
    public Optional<Representative> findByGrade(Integer grade) {
        return representativeRepository.findByGrade(grade);
    }

    @Override
    public Optional<Representative> findByName(String name) {
        return representativeRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        representativeRepository.deleteById(id);
    }
}
