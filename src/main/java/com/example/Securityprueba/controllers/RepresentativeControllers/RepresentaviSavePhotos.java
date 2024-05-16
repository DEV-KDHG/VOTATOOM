package com.example.Securityprueba.controllers.RepresentativeControllers;

import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import com.example.Securityprueba.service.representativeServices.RepresentativeServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@RestController
@RequestMapping(value = "/api/v1/representative")
public class RepresentaviSavePhotos {

    @Autowired
    private RepresentativeServicesImpl representativeServices;

    @PutMapping("/upload/representative/{identification}")
    public ResponseEntity<?> uploadImages(@RequestParam("file") MultipartFile file,
                                          @PathVariable Long identification) {
        try {
            Optional<Representative> optionalRepresentative = representativeServices.findByIdentification(identification);
            if (optionalRepresentative.isPresent()) {
                Representative representative = optionalRepresentative.get();
                representative.setNamePhoto(file.getOriginalFilename());
                representative.setData(file.getBytes());
                representativeServices.save(representative);
                return ResponseEntity.ok(representative);
            } else {
                return ResponseEntity.notFound().build(); // Personero not found with the given identification
            }
        } catch (IOException e) {
            return ResponseEntity.badRequest().build(); // Error reading or processing file
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Other unexpected errors
        }
    }
}


