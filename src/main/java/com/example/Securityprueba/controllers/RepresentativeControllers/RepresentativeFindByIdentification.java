package com.example.Securityprueba.controllers.RepresentativeControllers;

import com.example.Securityprueba.Dto.RepresentativeDTO.RepresentativeDTO;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.service.representativeServices.RepresentativeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/representative")
public class RepresentativeFindByIdentification {

    @Autowired
    private RepresentativeServices representativeServices;

    @GetMapping("/findByIdentification/{identification}")
    public ResponseEntity<?> findByIdentification(@PathVariable Long identification) {
        Representative representative = representativeServices.findByIdentification(identification)
                .orElse(null);

        if (representative == null){
            return ResponseEntity.notFound().build();
        }

        RepresentativeDTO representativeDTO = RepresentativeDTO.builder()
                .name(representative.getName())
                .lastName(representative.getLastName())
                .identification(representative.getIdentification())
                .grade(representative.getGrade())
                 .group(representative.getGroup())
                .build();

        return ResponseEntity.ok(representativeDTO);
    }

}