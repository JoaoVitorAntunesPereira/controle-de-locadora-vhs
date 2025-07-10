package br.edu.ifpr.foz.controle_de_locadora_vhs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.Genre;
import br.edu.ifpr.foz.controle_de_locadora_vhs.repositories.GenreRepository;
@Service
public class GenreService {
    
    @Autowired
    GenreRepository genreRepository;

    public List<Genre> findAll(){
        
        return genreRepository.findAll();
    }

    public Genre save(Genre genre) {

        return genreRepository.save(genre);
    }
}
