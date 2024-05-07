package com.example.Securityprueba.controllers.ComptrollerControllers;

import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.service.comptrollerServices.ComptrollerServicesIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/comptroller")
public class ComptrollerDeleteByIdentification {
    @Autowired
    private ComptrollerServicesIMP comptrollerServicesIMP;
@DeleteMapping("/DeleteById/{identification}")
    public ResponseEntity<?> deleteById(@PathVariable Long identification){
        Optional<Comptroller> findByIdentitification=comptrollerServicesIMP.findByIdentification(identification);
        if (!findByIdentitification.isPresent()){
            return ResponseEntity.notFound().build();

        }
       comptrollerServicesIMP.deleteByIdentification(identification);

        return ResponseEntity
                .ok("se elimino el contralor");
    }

}
