package br.edu.ifpr.foz.controle_de_locadora_vhs.conttrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.User;
import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.VHS;
import br.edu.ifpr.foz.controle_de_locadora_vhs.services.VHSService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @Autowired
    VHSService vhsService;

    @GetMapping({"","/","/home"})
    public String home(HttpSession session, Model model){

        List<VHS> vhsList = vhsService.findAll();
        model.addAttribute("vhsList", vhsList);

        return "vhs-list-2";
    }

}
