package br.edu.ifpr.foz.controle_de_locadora_vhs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.VHS;

public interface VHSRepository extends JpaRepository<VHS, Long>{
    
}
