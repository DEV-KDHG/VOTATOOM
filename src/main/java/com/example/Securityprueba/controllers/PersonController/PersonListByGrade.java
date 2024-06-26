package com.example.Securityprueba.controllers.PersonController;

import com.example.Securityprueba.Dto.PersoneroDto.PersoneroDTO;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/personero")
public class PersonListByGrade {
    @Autowired
    private PersonerosServicesImpl personerosServices;


    @GetMapping(value = "/findAllByGrade/{grade}")
    public ResponseEntity<?> findAllPersonerosByGrade(@PathVariable int grade) {
        List<PersoneroDTO> personeroDTOS = personerosServices.findAllByGrade(grade).stream()

                .map(personero -> PersoneroDTO.builder()
                        .name(personero.getName())

                        .identification(personero.getIdentification())
                        .grade(personero.getGrade())
                        .group(personero.getGroup())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(personeroDTOS);
    }
}
