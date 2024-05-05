package com.example.Securityprueba.repositories.CandidatesRepository;

import com.example.Securityprueba.Dto.RepresentativeDTO.RepresentativeDTO;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Long> {

    @Override
    Optional<Representative> findById(Long id);

    Optional<Representative> findByIdentification(Long identification);

    List<Representative> findByGrade(Integer grade);

    List<Representative> findAllByName(String name);

    List<Representative> findAllByGrade(Integer grade);

    void deleteByIdentification(Long identification);

}
