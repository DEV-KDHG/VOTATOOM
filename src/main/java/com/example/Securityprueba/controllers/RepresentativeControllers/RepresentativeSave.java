package com.example.Securityprueba.controllers.RepresentativeControllers;

import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.service.representativeServices.RepresentativeServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/representative")
public class RepresentativeSave {

    @Autowired
    private RepresentativeServicesImpl representativeServices;
    @PostMapping("/save")
    public ResponseEntity<String> saveRepresentative(@RequestBody Representative representative) {
        // Verificar si el representante ya existe
        if (representativeServices.findByIdentification(representative.getIdentification()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Representative with the given identification already exists.");
        }

        try {
            // Si no existe, guardar el representante
            representativeServices.save(representative);
            return ResponseEntity.status(HttpStatus.CREATED).body("Representative saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save representative.");
        }
    }

}

