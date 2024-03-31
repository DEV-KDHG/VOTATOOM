package com.example.Securityprueba.service;


import com.example.Securityprueba.entities.Users;
import com.example.Securityprueba.repository.AdministratorRepository;
import com.example.Securityprueba.repository.JuryRepository;
import com.example.Securityprueba.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private AdministratorRepository administratorRepository;
@Autowired
private JuryRepository juryRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> studentOptional = studentsRepository.findByUsername(username);
        if (studentOptional.isPresent()) {
            return studentOptional.get();
        }
        
        Optional<Users> administratorOptional = administratorRepository.findByUsername(username);
        if (administratorOptional.isPresent()) {
            return administratorOptional.get();
        }
        Optional<Users> JuryOptional = juryRepository.findByUsername(username);
        if (JuryOptional.isPresent()) {
            return JuryOptional.get();
        }



        // Si no se encuentra en ninguna tabla, lanza una excepción
        throw new UsernameNotFoundException("Usuario no encontrado");
    }
}
