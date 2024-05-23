package com.example.Securityprueba.controllers.PersonController;

import com.example.Securityprueba.Dto.PersoneroDto.PersoneroDTO;
import com.example.Securityprueba.Dto.PersoneroDto.PersoneroDtoList;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/personero")
public class PersonerListAllWitId {
    @Autowired
    private PersonerosServicesImpl personerosServices;
    @GetMapping(value = "/findAllWithId")
    public ResponseEntity<?>responseEntity(){
        List<PersoneroDtoList>personeroDTOS=personerosServices.findAll().stream().map(personero ->
                        PersoneroDtoList.builder()
                        .id(personero.getId())
                        .name(personero.getName())
                         .lastName(personero.getLastName())
                         .identification(personero.getIdentification())
                        .grade(personero.getGrade())
                        .group(personero.getGroup())
                        .data(personero.getData())
                                                .build()
                ).toList();
        return  ResponseEntity.ok(personeroDTOS);
    }
}
