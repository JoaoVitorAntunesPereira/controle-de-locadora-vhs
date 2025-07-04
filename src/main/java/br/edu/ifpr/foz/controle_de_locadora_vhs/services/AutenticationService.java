package br.edu.ifpr.foz.controle_de_locadora_vhs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.User;
import br.edu.ifpr.foz.controle_de_locadora_vhs.repositories.UserRepository;

@Service
public class AutenticationService {
    
    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User login(String email, String password){
        
        User user = userRepository.findByEmail(email).get();

        if(user == null || !encoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("Usuário não encontrado!");
        }

        return user;

    }

}
