package com.example.Securityprueba.repositories.CandidatesRepository;

import com.example.Securityprueba.entities.candidatesModels.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Long> {

    @Override
    Optional<Representative> findById(Long Long);

    Optional<Representative> findByGrade(Integer grade);

    Optional<Representative> findByName(String name);
}
