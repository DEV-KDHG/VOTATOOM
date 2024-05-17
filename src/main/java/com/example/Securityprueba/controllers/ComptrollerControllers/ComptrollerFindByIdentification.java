package com.example.Securityprueba.controllers.ComptrollerControllers;

import com.example.Securityprueba.Dto.ComptrollersDto.ComptrollerDTO;
import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.service.comptrollerServices.ComptrollerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/comptroller")
public class ComptrollerFindByIdentification {

    @Autowired

    private ComptrollerService comptrollerService;


    @GetMapping("/findByIdentification/{identification}")
    public ResponseEntity<?> findByIdentification(@PathVariable Long identification) {

        Comptroller comptroller = comptrollerService.findByIdentification(identification)
                .orElse(null);

        if (comptroller == null){
            return ResponseEntity.notFound().build();
        }

        ComptrollerDTO comptrollerDTO = ComptrollerDTO.builder()
                .name(comptroller.getName())
                .lastName(comptroller.getLastName())
                .identification(comptroller.getIdentification())
                .grade(comptroller.getGrade())
                 .group(comptroller.getGroup())
                .build();
        return ResponseEntity.ok(comptrollerDTO);
    }
}
