package com.example.Securityprueba.controllers.PersonController;

import com.example.Securityprueba.Dto.PersoneroDto.PersoneroDTO;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.util.Optional;
@RestController
@RequestMapping("/api/v1/personero")
public class PersonSavePhotos {

    @Autowired
    private PersonerosServicesImpl personerosServices;

    @PutMapping("/upload/personero/{identification}")
    public ResponseEntity<?> uploadImages(@RequestParam("file") MultipartFile file,
                                          @PathVariable Long identification) {
        try {
            Optional<Personero> optionalPersonero = personerosServices.findByIdentification(identification);
            if (optionalPersonero.isPresent()) {
                Personero personero = optionalPersonero.get();
                personero.setNamePhoto(file.getOriginalFilename());
                personero.setData(file.getBytes());
                personerosServices.save(personero);
                return ResponseEntity.ok(personero);
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
