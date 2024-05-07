package com.example.Securityprueba.controllers.ComptrollerControllers;

import com.example.Securityprueba.Dto.ComptrollersDto.ComptrollerDTO;
import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.service.comptrollerServices.ComptrollerServicesIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/comptroller")
public class ComptrollerFindByName {


        @Autowired
        private ComptrollerServicesIMP comptrollerServicesIMP;
        @GetMapping("/findByName/{name}")
        public ResponseEntity<?> findByName(@PathVariable String name) {
            Optional<Comptroller> findByNameComptroller= comptrollerServicesIMP.findByName(name);

            if (findByNameComptroller.isPresent()) {
                Comptroller comptroller = findByNameComptroller.get();
                ComptrollerDTO comptrollerDTO =ComptrollerDTO.builder()
                        .name(comptroller.getName())
                        .lastName(comptroller.getLastName())
                        .identification(comptroller.getIdentification())

                        .grade(comptroller.getGrade())
                        .photo(comptroller.getPhoto())
                        .build();


            }

            return ResponseEntity.ok( findByNameComptroller);

        }

}
