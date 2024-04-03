package com.example.Securityprueba.service.userServices;

import com.example.Securityprueba.entities.SecurityModels.AuthenticationResponse;
import com.example.Securityprueba.entities.SecurityModels.Role;
import com.example.Securityprueba.entities.UserModels.Administrators;
import com.example.Securityprueba.entities.UserModels.Jury;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.repositories.UserRepositories.AdministratorRepository;
import com.example.Securityprueba.repositories.UserRepositories.JuryRepository;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.repositories.UserRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    private final StudentsRepository studentRepository;
    private final AdministratorRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Autowired
    private JuryRepository juryRepository;


    private final AuthenticationManager authenticationManager;


    public AuthenticationService(StudentsRepository studentRepository, AdministratorRepository adminRepository, PasswordEncoder passwordEncoder, JwtService jwtService, @Qualifier("studentsRepository") UserRepository userRepository,  AuthenticationManager authenticationManager) {
        this.studentRepository = studentRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userRepository = userRepository;

        this.authenticationManager = authenticationManager;
    }
    public AuthenticationResponse register(Students studentRequest) {
        Students student = new Students();

        student.setUsername(studentRequest.getUsername());
        student.setName(studentRequest.getName());
        student.setRole(Role.STUDENT);
        student.setLastName(studentRequest.getLastName());
        student.setPassword(passwordEncoder.encode(studentRequest.getPassword()));

        student.setGrade(studentRequest.getGrade());
        student.setIdentification(studentRequest.getIdentification());
student.setCode(studentRequest.getCode());
        Students savedStudent = studentRepository.save(student);


        String token = jwtService.generateToken(savedStudent);

        return new AuthenticationResponse(token);
    }



    public AuthenticationResponse registerAdmin(Administrators request) {
        Administrators administrador = new Administrators();

        administrador.setUsername(request.getUsername());
        administrador.setName(request.getName());
        administrador.setRole(Role.ADMIN);
        administrador.setLastName(request.getLastName());
administrador.setNameIns(administrador.getNameIns());
        administrador.setPassword(passwordEncoder.encode(request.getPassword()));

        Administrators savedAdmin = adminRepository.save(administrador);
        String token = jwtService.generateToken(savedAdmin);

        return new AuthenticationResponse(token);
    }


    public AuthenticationResponse registerJury(Jury request) {
    Jury jury = new Jury();

        jury.setUsername(request.getUsername());
        jury.setName(request.getName());
        jury.setRole(Role.JURY);
        jury.setLastName(request.getLastName());
jury.setGradeASigne(request.getGradeASigne());
       jury.setPassword(passwordEncoder.encode(request.getPassword()));

        Jury savedJury = juryRepository.save(jury);

        String token = jwtService.generateToken(savedJury);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(Users request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Users user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        if (user instanceof Students) {
            Students students =  new Students();
            students.getCode();
            String token = jwtService.generateToken(user);
            return new AuthenticationResponse("Estudiante autenticado: " + token);
        } else if (user instanceof Administrators) {
            String token = jwtService.generateToken(user);
            return new AuthenticationResponse("Administrador autenticado: " + token);


        } else if (user instanceof Jury) {
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse("Jury autenticado: " + token);}


        else {
            throw new IllegalArgumentException("Tipo de usuario no compatible");
        }
    }

}








