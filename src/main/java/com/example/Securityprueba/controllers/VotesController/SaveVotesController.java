package com.example.Securityprueba.controllers.VotesController;

import com.example.Securityprueba.Dto.VotesDto.VotesDto;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.entities.votesModels.Votes;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.service.votesServices.VotesSeriviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Optional;
@RequestMapping(value = "api/v1/save")
@RestController

public class SaveVotesController {

    @Autowired
    private    VotesSeriviceImpl votesSerivice;

    @Autowired
    private StudentsRepository studentsRepository;
    @PostMapping("/votes")
    public ResponseEntity<?> save(@RequestBody VotesDto votesDto, Principal principal) throws URISyntaxException {
        String username = principal.getName();
        Optional<Users> optionalStudent = studentsRepository.findByUsername(username);

        if (optionalStudent.isPresent() && optionalStudent.get() instanceof Students) {
            Students student = (Students) optionalStudent.get();

            // Obtener el grado del estudiante autenticado
            Integer authenticatedUserGrade = student.getGrade();

            // Verificar si el representante seleccionado es del mismo grado que el estudiante autenticado
            if (votesDto.getRepresentative() != null && authenticatedUserGrade.equals(votesDto.getRepresentative().getGrade())) {
                // Crear un nuevo voto
                Votes vote = new Votes();
                vote.setPersonero(votesDto.getPersonero());
                vote.setComptroller(votesDto.getComptroller());
                vote.setRepresentative(votesDto.getRepresentative());
                vote.setStudentsId(student.getId());

                // Guardar el voto utilizando el servicio de votos
                votesSerivice.save(vote);

                // Actualizar el estado de votación del estudiante
                student.setStateVotation(true); // Cambiar el estado de votación a true
                studentsRepository.save(student);

                return ResponseEntity.created(new URI("/api/v1/votes/" + vote.getId())).build();
            } else {
                // El estudiante no puede votar por este representante
                return ResponseEntity.badRequest().body("No puedes votar por este representante.");
            }
        } else {
            // Usuario no encontrado o no es un estudiante
            return ResponseEntity.notFound().build();
        }
    }
}