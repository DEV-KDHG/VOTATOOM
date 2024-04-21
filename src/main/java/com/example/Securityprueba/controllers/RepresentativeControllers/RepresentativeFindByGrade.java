package com.example.Securityprueba.controllers.RepresentativeControllers;

import com.example.Securityprueba.Dto.RepresentativeDTO.RepresentativeDTO;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.service.representativeServices.RepresentativeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/representative")
public class RepresentativeFindByGrade {
    @Autowired
    private RepresentativeServices representativeServices;


    @GetMapping("/findByGrade/{grade}")
    public ResponseEntity<?> findByGrade(@PathVariable Integer grade) {
        List<Representative> representatives = representativeServices.findByGrade(grade);

        if (representatives.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<RepresentativeDTO> representativeDTOs = new ArrayList<>();

        for (Representative representative : representatives) {
            RepresentativeDTO representativeDTO = RepresentativeDTO.builder()
                    .name(representative.getName())
                    .lastName(representative.getLastName())
                    .identification(representative.getIdentification())
                    .grade(representative.getGrade())
                    .photo(representative.getPhoto())
                    .group(representative.getGroup())
                    .build();
            representativeDTOs.add(representativeDTO);
        }

        return ResponseEntity.ok(representativeDTOs);
    }

}
