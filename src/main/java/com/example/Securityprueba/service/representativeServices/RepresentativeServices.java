package com.example.Securityprueba.service.representativeServices;

import com.example.Securityprueba.entities.candidatesModels.Representative;

import java.util.List;
import java.util.Optional;

public interface RepresentativeServices {

    void save (Representative representative);

    Optional<Representative> findByIdentification(Long identification);

    List<Representative> findAll();

    Optional<Representative> findByGrade(Integer grade);

    List<Representative> findByName(String name);

    void deleteById(Long id);


}
