package com.example.Securityprueba.controllers.ComptrollerControllers;

import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import com.example.Securityprueba.service.comptrollerServices.ComptrollerServicesIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController

@RequestMapping(value = "/api/v1/comptroller")
public class ComptrollerSavePhotos {
    @Autowired
    private ComptrollerServicesIMP comptrollerServicesIMP;

    @PutMapping("/upload/comptroller/{identification}")
    public ResponseEntity<?> uploadImages(@RequestParam("file") MultipartFile file,
                                          @PathVariable Long identification) {
        try {
            Optional<Comptroller> comptrollerServicesIMPByIdentification = comptrollerServicesIMP.findByIdentification(identification);
            if (comptrollerServicesIMPByIdentification.isPresent()) {
                Comptroller comptroller = comptrollerServicesIMPByIdentification.get();
                comptroller.setNamePhoto(file.getOriginalFilename());
                comptroller.setData(file.getBytes());
                comptrollerServicesIMP.save(comptroller);
                return ResponseEntity.ok(comptroller);
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
