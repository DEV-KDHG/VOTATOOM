package com.example.Securityprueba.controllers.ComptrollerControllers;

import com.example.Securityprueba.Dto.ComptrollersDto.ComptrollerDTO;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.repositories.CandidatesRepository.ComptrollerRepository;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import com.example.Securityprueba.service.comptrollerServices.ComptrollerServicesIMP;
import com.example.Securityprueba.service.representativeServices.RepresentativeServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:5173")
@RestController

@RequestMapping(value = "/api/v1/comptroller")
public class ComptrollerSave {
    @Autowired
    private ComptrollerServicesIMP comptrollerServicesIMP;
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired

    private RepresentativeServicesImpl representativeServices;
    @Autowired
    private PersonerosServicesImpl personerosServices;

    @Autowired
    private ComptrollerRepository comptrollerRepository;

            @PostMapping("/save/comptroller")
    public ResponseEntity<?> save(@RequestBody ComptrollerDTO comptrollerDTO, Principal principal) throws URISyntaxException {
        String name = principal.getName();

        // Buscar al estudiante por identificación
        Optional<Students> existingStudent = studentsRepository.findStudentByIdentification(comptrollerDTO.getIdentification());
        if (!existingStudent.isPresent()) {
            return ResponseEntity.badRequest().body("Estudiante no encontrado. Debe registrar al estudiante primero.");
        }

        Students student = existingStudent.get();

        // Verificar que el estudiante esté en 10mo o 11mo grado
        if (student.getGrade() != 10 && student.getGrade() != 11) {
            return ResponseEntity.badRequest().body("El estudiante debe estar en 10mo o 11mo grado para asignar un controlador.");
        }

        // Verificar si ya existe un controlador para este estudiante

        Optional<Personero> existingPersoneros = personerosServices.findByIdentification(comptrollerDTO.getIdentification());

        Optional<Representative> existingRepresentative = representativeServices.findByIdentification(comptrollerDTO.getIdentification());
        Optional<Comptroller> existingComptroller = comptrollerRepository.findByIdentification(comptrollerDTO.getIdentification());

        if (existingComptroller.isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe una postulación para este candidato");
        }
        if (existingRepresentative.isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe una postulación para este candidato");
        }
        if (existingRepresentative.isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe una postulación para este candidato");
        }


        // Crear un nuevo Comptroller solo si el estudiante es de 10mo o 11mo grado
        Comptroller newComptroller = new Comptroller();
        newComptroller.setName(student.getName());
        newComptroller.setLastName(student.getLastName());
        newComptroller.setIdentification(student.getIdentification());
        newComptroller.setGrade(student.getGrade());
        newComptroller.setGroup(student.getGroup());

        comptrollerServicesIMP.save(newComptroller);

        return ResponseEntity.ok("Controlador creado exitosamente.");
    }
}