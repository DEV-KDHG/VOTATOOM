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

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(value = "/api/v1/comptroller")
public class ComptrollerDelete {
    @Autowired
    private ComptrollerServicesIMP comptrollerServicesIMP;
@DeleteMapping("/DeleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Optional<Comptroller> findById=comptrollerServicesIMP.findById(id);
        if (!findById.isPresent()){
            return ResponseEntity.notFound().build();

        }
       comptrollerServicesIMP.deleteById(id);

        return ResponseEntity
                .ok("se elimino el contralor");
    }

}
