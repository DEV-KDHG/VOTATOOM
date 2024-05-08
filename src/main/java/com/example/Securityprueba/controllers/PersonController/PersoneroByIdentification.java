package com.example.Securityprueba.controllers.PersonController;

import com.example.Securityprueba.Dto.PersoneroDto.PersoneroDTO;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/personero")
public class PersoneroByIdentification {

    @Autowired

    private PersonerosServices personerosServices;

    @GetMapping("/findByIdentification/{identification}")
        public ResponseEntity<?> findByIdentification(@PathVariable Long identification) {
        Personero personero = personerosServices.findByIdentification(identification)
                .orElse(null);

        if (personero == null){
            return ResponseEntity.notFound().build();
        }

            PersoneroDTO personeroDTO = PersoneroDTO.builder()
                    .name(personero.getName())
                    .lastName(personero.getLastName())
                    .identification(personero.getIdentification())
                    .grade(personero.getGrade())
                    .photo(personero.getPhoto())
                    .group(personero.getGroup())
                    .build();

        return ResponseEntity.ok(personeroDTO);
    }
}
