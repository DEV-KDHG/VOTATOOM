package com.example.Securityprueba.controllers.RepresentativeControllers;

import com.example.Securityprueba.service.representativeServices.RepresentativeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/representative")
public class RepresentativeDeleteByIdentification {
    @Autowired
    private RepresentativeServices representativeServices;

    @DeleteMapping("/deleteByIdentification/{identification}")
    public ResponseEntity<String> deleteById(@PathVariable Long identification) {
        try {
            representativeServices.deleteByIdentification(identification);
            return ResponseEntity.ok("Representative with ID: " + identification + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete representative with ID: " + identification);
        }
    }
}
