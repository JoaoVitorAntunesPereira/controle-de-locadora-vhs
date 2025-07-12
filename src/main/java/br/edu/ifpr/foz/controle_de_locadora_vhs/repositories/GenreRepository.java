package br.edu.ifpr.foz.controle_de_locadora_vhs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifpr.foz.controle_de_locadora_vhs.dto.GenreWithVHSCount;
import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{
    
    @Query(value = "SELECT g.*, COUNT(*) count FROM genre g JOIN vhs v ON g.id = v.genre_id GROUP BY g.id", nativeQuery = true)
    public List<GenreWithVHSCount> findAllWithVhsCount();

}
