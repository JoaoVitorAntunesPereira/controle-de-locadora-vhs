package br.edu.ifpr.foz.controle_de_locadora_vhs.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.User;
import br.edu.ifpr.foz.controle_de_locadora_vhs.repositories.UserRepository;


@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void save(User user) throws IllegalArgumentException{
        
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email ja cadastrado");
        }

        String ePass = encoder.encode(user.getPassword());

        user.setPassword(ePass);

        userRepository.save(user);

    }


}
