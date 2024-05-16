package com.example.Securityprueba.controllers.PersonController;

import com.example.Securityprueba.Dto.PersoneroDto.PersoneroDTO;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/personero")
public class PersoneroFindById {
    @Autowired
    private PersonerosServicesImpl personerosServices;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        // Buscar el Personero por su ID en la base de datos
        Personero personero = personerosServices.findById(id)
                .orElse(null);

        if (personero == null) {
            // Si no se encuentra el Personero, devolver una respuesta 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Convertir el Personero encontrado a un DTO para la respuesta
        PersoneroDTO personeroDTO = PersoneroDTO.builder()
                .name(personero.getName())
                 .identification(personero.getIdentification())
                .grade(personero.getGrade())
                .group(personero.getGroup())
                .build();

        // Devolver el DTO del Personero encontrado como respuesta 200 OK
        return ResponseEntity.ok(personeroDTO);
    }
}

