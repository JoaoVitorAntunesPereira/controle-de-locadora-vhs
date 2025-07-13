package br.edu.ifpr.foz.controle_de_locadora_vhs.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.VHS;
import br.edu.ifpr.foz.controle_de_locadora_vhs.repositories.VHSRepository;

@Service
public class VHSService {
    
    @Autowired
    VHSRepository vhsRepository;
    
    public List<VHS> findAll(){
        return vhsRepository.findAll();
    }

    public Optional<VHS> findById(Long id){
        return vhsRepository.findById(id);
    }

    public VHS save(VHS vhs) {

        if(vhs.getRegistrationDate() == null){
            vhs.setRegistrationDate(LocalDate.now());
        }

        return vhsRepository.save(vhs);
    }

    public boolean delete(Long id) throws IOException{

        Optional<VHS> vhsOptional = vhsRepository.findById(id);

        if(vhsOptional.isPresent()){
            VHS vhs = vhsOptional.get();

            vhsRepository.delete(vhs);

            String UPLOAD_DIR = new File("src/main/resources/static/uploads/").getAbsolutePath();
            String fileName = vhs.getImage();
            Files.delete(Path.of(UPLOAD_DIR,fileName));

            return true;
        }

        return false;

    }

}
