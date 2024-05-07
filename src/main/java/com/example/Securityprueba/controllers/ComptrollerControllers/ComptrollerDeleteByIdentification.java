package com.example.Securityprueba.controllers.ComptrollerControllers;

import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.service.comptrollerServices.ComptrollerServicesIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
