package br.edu.ifpr.foz.controle_de_locadora_vhs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.VHS;

public interface VHSRepository extends JpaRepository<VHS, Long>{

    @Query("SELECT v FROM VHS v WHERE v.genre.id = :genreId")
    List<VHS> findByGenre(@Param("genreId") Long genreId);
    
}
