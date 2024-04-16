package com.example.Securityprueba.dao.RepresentativeDao;

import com.example.Securityprueba.Dto.RepresentativeDTO.RepresentativeDTO;
import com.example.Securityprueba.entities.candidatesModels.Representative;

import java.util.List;
import java.util.Optional;

public interface RepresentativeDao {
    void save (Representative representative);

    List<RepresentativeDTO> findAll();

    Optional<Representative> findByIdentification(Long identification);

    Optional<Representative> findByGrade(Integer grade);

    List<Representative> findByName(String name);

    void deleteById(Long id);
}
