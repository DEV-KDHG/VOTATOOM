package com.example.Securityprueba.dao.RepresentativeDao;

import com.example.Securityprueba.Dto.RepresentativeDTO.RepresentativeDTO;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.repositories.CandidatesRepository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class RepresentativeDaoImpl implements RepresentativeDao{

    @Autowired
    private RepresentativeRepository representativeRepository;


    @Override
    public void save(Representative representative) {
        representativeRepository.save(representative);
    }

    @Override
    public List<RepresentativeDTO> findAll() {
        List<Representative> representatives = representativeRepository.findAll();
        return representatives.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Representative> findById(Long id) {
        return representativeRepository.findById(id);
    }

    private RepresentativeDTO convertToDto(Representative representative) {
        return RepresentativeDTO.builder()
                .name(representative.getName())
                .lastName(representative.getLastName())
                .identification(representative.getIdentification())
                .grade(representative.getGrade())
                 .group(representative.getGroup())
                .build();
    }



    @Override
    public Optional<Representative> findByIdentification(Long identification){
        return representativeRepository.findByIdentification(identification);
    }

    @Override
    public List<Representative> findByGrade(Integer grade) {
        return representativeRepository.findByGrade(grade);
    }

    @Override
    public List<Representative> findByName(String name) {
        return representativeRepository.findAllByName(name);
    }

    @Override
    public void deleteByIdentification(Long identification) {
        representativeRepository.deleteByIdentification(identification);
    }
}
