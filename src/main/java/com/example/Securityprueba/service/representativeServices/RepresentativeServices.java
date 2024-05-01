package com.example.Securityprueba.service.representativeServices;

import com.example.Securityprueba.Dto.RepresentativeDTO.RepresentativeDTO;
import com.example.Securityprueba.entities.candidatesModels.Representative;

import java.util.List;
import java.util.Optional;

public interface RepresentativeServices {

    void save (Representative representative);
    Optional<Representative>findById(Long id);

    Optional<Representative> findByIdentification(Long identification);

    List<RepresentativeDTO> findAll();

    List<Representative> findByGrade(Integer grade);

    List<Representative> findByName(String name);

    void deleteByIdentification(Long identification);


}
