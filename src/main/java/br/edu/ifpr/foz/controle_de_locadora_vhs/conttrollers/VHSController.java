package br.edu.ifpr.foz.controle_de_locadora_vhs.conttrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/view/{id}")
    public String vhsView(Model model, @PathVariable Long id){

        VHS vhs = vhsService.findById(id).get();

        if(vhs != null){
            model.addAttribute("vhs", vhs);

            return "vhs-view";
        }else{
            return "redirect:/vhs-list";
        }

    }
}
