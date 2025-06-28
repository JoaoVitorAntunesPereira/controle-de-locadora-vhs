package br.edu.ifpr.foz.controle_de_locadora_vhs.conttrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.VHS;
import br.edu.ifpr.foz.controle_de_locadora_vhs.services.VHSService;

@Controller
@RequestMapping("/vhs")
public class VHSController {
    
    @Autowired
    VHSService vhsService;

    @GetMapping
    public String findAll(Model model){
        
        List<VHS> vhsList = vhsService.findAll();
        model.addAttribute("vhsList", vhsList);

        return "vhs-list";
    }
}
