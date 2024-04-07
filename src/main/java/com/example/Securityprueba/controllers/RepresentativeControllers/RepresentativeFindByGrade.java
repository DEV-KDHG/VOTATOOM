package com.example.Securityprueba.controllers.RepresentativeControllers;

import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.service.representativeServices.RepresentativeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/representative")
public class RepresentativeFindByGrade {
    @Autowired
    private RepresentativeServices representativeServices;

    @GetMapping("/findByGrade/{grade}")
    public ResponseEntity<?> findByGrade(@PathVariable Integer grade) {
        Optional<Representative> representativeOptional = representativeServices.findByGrade(grade);
        if (representativeOptional.isPresent()) {
            return ResponseEntity.ok(representativeOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No representative found with grade: " + grade);
        }
    }
}
