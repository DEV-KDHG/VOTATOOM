package com.example.Securityprueba.dao.RepresentativeDao;

import com.example.Securityprueba.Dto.RepresentativeDTO.RepresentativeDTO;
import com.example.Securityprueba.entities.candidatesModels.Representative;

import java.util.List;
import java.util.Optional;

public interface RepresentativeDao {
    void save (Representative representative);

    List<RepresentativeDTO> findAll();
Optional<Representative>findById(Long id);
    Optional<Representative> findByIdentification(Long identification);

    List<Representative> findByGrade(Integer grade);

    List<Representative> findByName(String name);

    void deleteByIdentification(Long identification);
}
