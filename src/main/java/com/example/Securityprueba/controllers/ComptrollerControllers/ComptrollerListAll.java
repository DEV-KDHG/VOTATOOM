package com.example.Securityprueba.controllers.ComptrollerControllers;

import com.example.Securityprueba.Dto.ComptrollersDto.ComptrollerDTO;
import com.example.Securityprueba.service.comptrollerServices.ComptrollerServicesIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/api/v1/comptroller")
public class ComptrollerListAll {
    @Autowired
    private ComptrollerServicesIMP comptrollerServicesIMP;

    @GetMapping("/list/comptroller")
    public ResponseEntity<?> list(){
        List <ComptrollerDTO> comptrollerDTOS = comptrollerServicesIMP.findAll().stream().map(
                comptroller -> ComptrollerDTO.builder()
                        .name(comptroller.getName())
                        .lastName(comptroller.getLastName())
                        .grade(comptroller.getGrade())
                        .data(comptroller.getData())

                        .identification(comptroller.getIdentification())
                        .build()
        ).toList();
        return ResponseEntity.ok(comptrollerDTOS);
    }


}
