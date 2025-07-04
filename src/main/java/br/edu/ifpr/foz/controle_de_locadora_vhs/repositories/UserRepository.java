package br.edu.ifpr.foz.controle_de_locadora_vhs.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public Optional<User> findByEmail(String email);

}
