package br.edu.ifpr.foz.controle_de_locadora_vhs.conttrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @GetMapping({"","/","/home"})
    public String home(HttpSession session, Model model){

        User user = (User) session.getAttribute("loggedUser");

        model.addAttribute("user", user);

        return "home";
    }

}
