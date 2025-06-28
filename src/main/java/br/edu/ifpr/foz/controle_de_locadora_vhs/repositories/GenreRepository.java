package br.edu.ifpr.foz.controle_de_locadora_vhs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{
    
}
