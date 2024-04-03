package com.example.Securityprueba.controllers.ComptrollerControllers;

import com.example.Securityprueba.Dto.ComptrollersDTO.ComptrollerDTO;
import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.service.comptrollerServices.ComptrollerServicesIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class ComptrollerSave {
    @Autowired
    private ComptrollerServicesIMP comptrollerServicesIMP;
   @PostMapping(value = "/save/comptroller")
  public ResponseEntity<?> save(@RequestBody ComptrollerDTO comptroller) throws URISyntaxException {
    // Crear una instancia de Comptroller utilizando el constructor de la superclase Candidate
      Comptroller newComptroller = new Comptroller();
newComptroller.setName(comptroller.getName());
newComptroller.setGrade(comptroller.getGrade());
newComptroller.setIdentification(comptroller.getIdentification());
newComptroller.setLastName(comptroller.getLastName());
newComptroller.setPhoto(comptroller.getPhoto());
    comptrollerServicesIMP.save(newComptroller);

     return ResponseEntity.ok("Comptroller saved successfully");
   }
}
