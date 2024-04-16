package com.example.Securityprueba.controllers.VotesController;

import com.example.Securityprueba.service.votesServices.VotesSeriviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/ordenarDeMayorAMenor")
public class OrganizarVotosDeMayoaMenorComptroller {
@Autowired
    private VotesSeriviceImpl votesSerivice;
@GetMapping(value = "/Comptroller")
public ResponseEntity<?>findGetMayorAMenor (){
    return ResponseEntity.ok(ResponseEntity.ok());
}
}
