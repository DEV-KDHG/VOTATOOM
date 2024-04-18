package com.example.Securityprueba.controllers.RepresentativeControllers;

import com.example.Securityprueba.Dto.RepresentativeDTO.RepresentativeDTO;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.repositories.CandidatesRepository.ComptrollerRepository;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServices;
import com.example.Securityprueba.service.representativeServices.RepresentativeServices;
import com.example.Securityprueba.service.representativeServices.RepresentativeServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/representative")
public class RepresentativeSave {

    @Autowired
    private RepresentativeServices representativeServices;

    @Autowired
    private RepresentativeServicesImpl representativeServicesImplc;

    @Autowired
    private StudentsRepository studentsRepository;


    @Autowired
    private PersonerosServices personerosServices;



    @Autowired
    private ComptrollerRepository comptrollerRepository;

    @PostMapping("/save")
    public ResponseEntity<?> saveRepresentative(@RequestBody RepresentativeDTO representativeDTO) {
        try {
            // Buscar al estudiante por identificación
            Optional<Students> optionalStudents = studentsRepository.findStudentByIdentification(representativeDTO.getIdentification());

            if (optionalStudents.isEmpty()) {
                return ResponseEntity.badRequest().body("Estudiante no encontrado. Debe registrar al estudiante primero.");
            }

            Students students = optionalStudents.get();

            // Verificar si el grado del estudiante es mayor a 3 para crear al representante
            if (students.getGrade() == null || students.getGrade() <= 3) {
                return ResponseEntity.badRequest().body("No se puede registrar porque el grado del estudiante no es mayor a 3");
            }

            // Verificar si ya existe un representante asociado a esta identificación
            Optional<Representative> existingRepresentative = representativeServices.findByIdentification(representativeDTO.getIdentification());
            Optional<Comptroller> existingComptroller = comptrollerRepository.findByIdentification(representativeDTO.getIdentification());
            Optional<Personero> existingPersonero = personerosServices.findByIdentification(representativeDTO.getIdentification());

            if (existingRepresentative.isPresent()) {
                return ResponseEntity.badRequest().body("No se puede registrar porque ya existe un Representante para esta identificación");
            }

            if (existingComptroller.isPresent()) {
                return ResponseEntity.badRequest().body("No se puede registrar porque ya existe un Representante para esta identificación");
            }
            if (existingPersonero.isPresent()) {
                return ResponseEntity.badRequest().body("No se puede registrar porque ya existe un Representante para esta identificación");
            }
            // Crear un nuevo representante utilizando los datos del estudiante y RepresentativeDTO
            Representative representative = new Representative();
            representative.setName(students.getName());
            representative.setLastName(students.getLastName());
            representative.setIdentification(representativeDTO.getIdentification());
            representative.setGrade(students.getGrade());
            representative.setGroup(students.getGroup());
            representative.setPhoto(representativeDTO.getPhoto());

            // Guardar el nuevo representante en la base de datos
            representativeServices.save(representative);

            // Retornar una respuesta con código 201 Created y la ubicación del recurso creado
            return ResponseEntity.created(new URI("/api/v1/representatives/" + representative.getId())).build();
        } catch (Exception e) {
            // Capturar cualquier excepción y devolver un error interno del servidor
            return ResponseEntity.status(500).body("Se produjo un error al procesar la solicitud.");
        }
    }
}