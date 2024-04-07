package com.example.Securityprueba.controllers.PersonController;

import com.example.Securityprueba.Dto.PersoneroDto.PersoneroDTO;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServices;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/save")
    public ResponseEntity<?> savePersonero(@RequestBody PersoneroDTO personeroDTO) throws URISyntaxException {

        // Buscar el estudiante por identificación
        Optional<Students> optionalStudents = studentsRepository.findByIdentification(personeroDTO.getIdentification());

        if (optionalStudents.isEmpty()) {
            // Si no se encuentra el estudiante, retornar un error 404 Not Found
            return ResponseEntity.notFound().build();
        }

        Students students = optionalStudents.get();

        // Verificar si el grado del estudiante es mayor a 3 para crear el Personero
        if (students.getGrade() > 3) {

            // Verificar si ya existe un Personero asociado a esta identificación
            Optional<Personero> existingPersonero = personerosServices.findByIdentification(personeroDTO.getIdentification());

            if (existingPersonero.isPresent()) {
                // Si ya existe un Personero con la misma identificación, retornar un mensaje indicando que no se puede registrar
                return ResponseEntity.badRequest().body("No se puede registrar porque ya existe un Personero para esta identificación");
            }

            // Crear un nuevo Personero utilizando los datos del estudiante y PersoneroDTO
            Personero personero = new Personero();
            personero.setName(students.getName());
            personero.setLastName(students.getLastName());
            personero.setIdentification(personeroDTO.getIdentification());
            personero.setGrade(students.getGrade());
            personero.setGroup(students.getGroup());
            personero.setPhoto(personeroDTO.getPhoto());

            // Guardar el nuevo Personero en la base de datos
      personerosServicesImplc.save(personero);

            // Retornar una respuesta con código 201 Created y la ubicación del recurso creado
            return ResponseEntity.created(new URI("/api/v1/personero/" )).build();
        }

        // Si el grado del estudiante no es mayor a 3, retornar un mensaje indicando que no se pudo crear el Personero
        return ResponseEntity.badRequest().body("No se puede registrar porque el grado del estudiante no es mayor a 3");
    }}