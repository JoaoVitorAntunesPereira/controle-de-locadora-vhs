package br.edu.ifpr.foz.controle_de_locadora_vhs.conttrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.User;
import br.edu.ifpr.foz.controle_de_locadora_vhs.services.AutenticationService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AutenticationController {
    
    @Autowired
    AutenticationService autenticationService;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, RedirectAttributes model, HttpSession session){
        
        try {
            User user = autenticationService.login(email, password);

            session.setAttribute("loggedUser", user);

            return "redirect:/home";

        } catch (Exception e) {
            model.addFlashAttribute("message", e.getMessage());

            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "redirect:/login";

    }

}
