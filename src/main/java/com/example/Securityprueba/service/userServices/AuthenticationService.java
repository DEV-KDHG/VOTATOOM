package com.example.Securityprueba.service.userServices;

import com.example.Securityprueba.Dto.StudentsDTO.LoginStudentDto;
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
import org.springframework.security.authentication.BadCredentialsException;
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
    public AuthenticationResponse registerStudent(Students studentRequest) {
        if (studentRepository.existsByUsername(studentRequest.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        }
        if (studentRepository.existsByIdentification(studentRequest.getIdentification())) {
            throw new IllegalArgumentException("La identificación ya está en uso.");
        }

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

    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public AuthenticationResponse registerAdmin(Administrators request) {
        Administrators administrador = new Administrators();
        if (adminRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        }
        if (adminRepository.existsByIdentification(request.getIdentification())) {
            throw new IllegalArgumentException("La identificación ya está en uso.");
        }
        administrador.setUsername(request.getUsername());
        administrador.setName(request.getName());
        administrador.setRole(Role.ADMIN);
        administrador.setIdentification(request.getIdentification());
        administrador.setLastName(request.getLastName());
administrador.setNameIns(administrador.getNameIns());
        administrador.setPassword(passwordEncoder.encode(request.getPassword()));

        Administrators savedAdmin = adminRepository.save(administrador);
        String token = jwtService.generateToken(savedAdmin);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse registerJury(Jury request) {
        // Verificar si el nombre de usuario ya está en uso
        if (juryRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario '" + request.getUsername() + "' ya está en uso.");
        }

        // Verificar si la identificación ya está en uso
        if (juryRepository.existsByIdentification(request.getIdentification())) {
            throw new IllegalArgumentException("La identificación '" + request.getIdentification() + "' ya está en uso.");
        }

        // Guardar el nuevo jurado en la base de datos
        Jury jury = new Jury();
        jury.setUsername(request.getUsername());
        jury.setName(request.getName());
        jury.setRole(Role.JURY);
        jury.setIdentification(request.getIdentification());
        jury.setLastName(request.getLastName());
        jury.setGradeASigne(request.getGradeASigne());
        jury.setPassword(passwordEncoder.encode(request.getPassword()));
        // Generar el token JWT para el jurado registrado
        String token = jwtService.generateToken(jury);
        juryRepository.save(jury);

        // Crear la respuesta de autenticación que incluye el token
        AuthenticationResponse response = new AuthenticationResponse("Jurado registrado exitosamente.");
        response.setToken(token);

        return response;

    }

    public AuthenticationResponse authenticateAllLessStudents(Users request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Users user = userRepository.findByUsername(request.getUsername()).orElseThrow();

         if (user instanceof Administrators) {
            String token = jwtService.generateToken(user);
            return new AuthenticationResponse( token);


        } else if (user instanceof Jury) {
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse("Jury autenticado: " + token);}


        else {
            throw new IllegalArgumentException("Tipo de usuario no compatible");
        }
    }

    public AuthenticationResponse authenticateStudent(LoginStudentDto request) {
        // Buscar al estudiante por nombre de usuario
        Students student = userRepository.findStudentByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas: Nombre de usuario no encontrado"));

        // Verificar la contraseña
        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas: Contraseña incorrecta");
        }

        // Verificar el código único
        if (!request.getCode().equals(student.getCode())) {
            throw new BadCredentialsException("Credenciales inválidas: Código único incorrecto");
        }

        // Generar un token JWT si las credenciales son válidas
        String token = jwtService.generateToken(student);

        // Devolver una instancia de AuthenticationResponse con el token generado
        return new AuthenticationResponse(token);
    }
}








