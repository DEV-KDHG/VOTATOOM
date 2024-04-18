package com.example.Securityprueba.controllers.ComptrollerControllers;

import com.example.Securityprueba.Dto.ComptrollersDto.ComptrollerDTO;
import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.service.comptrollerServices.ComptrollerServicesIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
@RequestMapping(value = "/api/v1/comptroller")
public class ComptrollerFindByGrade {
    @Autowired
    private ComptrollerServicesIMP comptrollerServicesIMP;
    @GetMapping("/findByGrade/{grade}")
    public ResponseEntity<?> findByGrade(@PathVariable Integer grade) {
        Optional<Comptroller> findByGradeComptroller= comptrollerServicesIMP.findByGrade(grade);

        if (findByGradeComptroller.isPresent()) {
            Comptroller comptroller = findByGradeComptroller.get();
            ComptrollerDTO comptrollerDTO =ComptrollerDTO.builder()
                    .name(comptroller.getName())
                    .lastName(comptroller.getLastName())
                    .identification(comptroller.getIdentification())
                    .grade(comptroller.getGrade())
                    .photo(comptroller.getPhoto())
                    .build();


        }

        return ResponseEntity.ok(findByGradeComptroller);

    }
}
