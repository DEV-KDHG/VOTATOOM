package com.example.Securityprueba.controllers.PersonController;

import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/personero")
public class SavePerson {
    @Autowired
    private PersonerosServicesImpl personerosServicesImplc;
}
