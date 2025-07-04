package br.edu.ifpr.foz.controle_de_locadora_vhs.conttrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.User;
import br.edu.ifpr.foz.controle_de_locadora_vhs.services.UserService;


@Controller
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String registerForm() {

        return "register";
    }
    
    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String email, @RequestParam String password, RedirectAttributes model){

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        try {
            userService.save(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addFlashAttribute("message", e);
            return "redirect:/register";
        }
    }


}
