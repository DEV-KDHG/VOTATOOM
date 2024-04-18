package com.example.Securityprueba.controllers.PersonController;

import com.example.Securityprueba.Dto.PersoneroDto.PersoneroDTO;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.repositories.CandidatesRepository.ComptrollerRepository;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServices;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import com.example.Securityprueba.service.comptrollerServices.ComptrollerServicesIMP;
import com.example.Securityprueba.service.representativeServices.RepresentativeServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/personero")
public class SavePerson {

    @Autowired
    private PersonerosServicesImpl personerosServicesImplc;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private PersonerosServices personerosServices;
    @Autowired

    private RepresentativeServicesImpl representativeServices;


    @Autowired
    private ComptrollerRepository comptrollerRepository;


        @PostMapping("/save")
        public ResponseEntity<?> savePersonero(@RequestBody PersoneroDTO personeroDTO) throws URISyntaxException {
            // Buscar al estudiante por identificación
            Optional<Students> optionalStudents = studentsRepository.findStudentByIdentification(personeroDTO.getIdentification());
            if (optionalStudents.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Students students = optionalStudents.get();

            // Verificar que el estudiante esté en grado 11
            if (students.getGrade() != 11) {
                return ResponseEntity.badRequest().body("El estudiante debe estar en grado 11 para ser Personero.");
            }
            Optional<Comptroller> existingComptroller = comptrollerRepository.findByIdentification(personeroDTO.getIdentification());
            Optional<Representative> existingRepresentate = representativeServices.findByIdentification(personeroDTO.getIdentification());

            // Verificar si ya existe un Personero asociado a esta identificación
            Optional<Personero> existingPersonero = personerosServices.findByIdentification(personeroDTO.getIdentification());
            if (existingPersonero.isPresent()) {
                return ResponseEntity.badRequest().body("Ya existe un Personero asociado a esta identificación.");
            }
            if (existingComptroller.isPresent()) {
                return ResponseEntity.badRequest().body("Ya existe un Personero asociado a esta identificación.");
            }
            if (existingRepresentate.isPresent()) {
                return ResponseEntity.badRequest().body("Ya existe un Personero asociado a esta identificación.");
            }

            // Crear un nuevo Personero y asignar los valores
            Personero personero = new Personero();
            personero.setName(students.getName());
            personero.setLastName(students.getLastName());
            personero.setIdentification(personeroDTO.getIdentification());
            personero.setGrade(students.getGrade());
            personero.setGroup(students.getGroup());
            personero.setPhoto(personeroDTO.getPhoto());

            // Guardar el nuevo Personero
            try {
                personerosServicesImplc.save(personero);
                URI location = new URI("/api/v1/personero/" + personero.getId()); // Usar la URI completa del recurso creado
                return ResponseEntity.created(location).build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el Personero.");
            }
        }
    }