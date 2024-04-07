package com.example.Securityprueba.dao.RepresentativeDao;

import com.example.Securityprueba.entities.candidatesModels.Representative;

import java.util.List;
import java.util.Optional;

public interface RepresentativeDao {
    void save (Representative representative);

    List<Representative> findAll();

    Optional<Representative> findByIdentification(Long identification);

    Optional<Representative> findByGrade(Integer grade);

    Optional<Representative> findByName(String name);

    void deleteById(Long id);
}
