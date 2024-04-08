package com.example.Securityprueba.controllers.ComptrollerControllers;

import com.example.Securityprueba.Dto.ComptrollersDto.ComptrollerDTO;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.repositories.CandidatesRepository.ComptrollerRepository;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.service.comptrollerServices.ComptrollerServicesIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Optional;
@RestController
@RequestMapping(value = "/api/v1/comptroller")
public class ComptrollerSave {
    @Autowired
    private ComptrollerServicesIMP comptrollerServicesIMP;
    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private ComptrollerRepository comptrollerRepository;


    @PostMapping("/save/comptroller")
    public ResponseEntity<?> save(@RequestBody ComptrollerDTO comptrollerDTO, Principal principal ) throws URISyntaxException {
        String name = principal.getName();




        Optional<Students> existingStudent = studentsRepository.findStudentByIdentification(comptrollerDTO.getIdentification());



        if (!existingStudent.isPresent()) {
            return ResponseEntity.badRequest().body("Estudiante no encontrado. Debe registrar al estudiante primero.");
        }


        Optional<Comptroller> existingComptroller = comptrollerRepository.findByIdentification(comptrollerDTO.getIdentification());
        if (existingComptroller.isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe un controlador para este estudiante.");
        }


        Comptroller newComptroller = new Comptroller();
        Students student = existingStudent.get();
        newComptroller.setName(student.getName());
        newComptroller.setLastName(student.getLastName());
        newComptroller.setIdentification(student.getIdentification());
        newComptroller.setGrade(student.getGrade());
        newComptroller.setStudentsId(student.getId());
        newComptroller.setGroup(student.getGroup());
        newComptroller.setEstado(comptrollerDTO.getEstado());
        newComptroller.setPhoto(comptrollerDTO.getPhoto());


        comptrollerServicesIMP.save(newComptroller);

        return ResponseEntity.ok("Controlador creado exitosamente.");

    }}