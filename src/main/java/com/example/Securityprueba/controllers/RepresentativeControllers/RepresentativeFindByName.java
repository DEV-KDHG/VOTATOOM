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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/representative")
public class RepresentativeFindByName {

    @Autowired
    private RepresentativeServices representativeServices;

@GetMapping("/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
    List<RepresentativeDTO> representativeDTOS = representativeServices.findByName(name).stream()
            .map(representative -> RepresentativeDTO.builder()
                    .name(representative.getName())
                    .lastName(representative.getLastName())
                    .identification(representative.getIdentification())
                    .photo(representative.getPhoto())
                    .grade(representative.getGrade())
                    .group(representative.getGroup())
                    .build())
            .collect(Collectors.toList());
    return ResponseEntity.ok(representativeDTOS);

}

}
