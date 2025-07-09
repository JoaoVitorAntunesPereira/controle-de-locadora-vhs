package br.edu.ifpr.foz.controle_de_locadora_vhs.conttrollers;

import java.util.List;
import java.util.Optional;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.User;
import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.VHS;
import br.edu.ifpr.foz.controle_de_locadora_vhs.services.GenreService;
import br.edu.ifpr.foz.controle_de_locadora_vhs.services.VHSService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/vhs")
public class VHSController {
    
    @Autowired
    VHSService vhsService;

    @Autowired
    GenreService genreService; 


    @GetMapping
    public String findAll(Model model, HttpSession session){
        
        User user = (User) session.getAttribute("loggedUser");

        List<VHS> vhsList = vhsService.findAll();
        model.addAttribute("vhsList", vhsList);
        model.addAttribute("user", user);

        return "vhs-list";
    }

    @GetMapping("/view/{id}")
    public String vhsView(Model model, @PathVariable Long id){

        Optional<VHS> vhs = vhsService.findById(id);

        if(vhs.isPresent()){
            model.addAttribute("vhs", vhs.get());

            return "vhs-view";
        }else{
            return "redirect:/vhs";
        }
    }

    @GetMapping("/add")
    public String addForm(Model model){

        VHS vhs = new VHS();
        model.addAttribute("vhs", vhs);
        model.addAttribute("statusList", Status.values());
        model.addAttribute("genres", genreService.findAll());

        return "vhs-form";
    }

    @PostMapping("/add")
    public String add(@Valid VHS vhs, BindingResult fields, Model model){
        
        if(fields.hasErrors()){
            model.addAttribute("vhs", vhs);
            model.addAttribute("statusList", Status.values());

            model.addAttribute("fields", fields);
            model.addAttribute("genres", genreService.findAll());
            return "vhs-form";
        }else{
            vhsService.save(vhs);

            return "redirect:/vhs";
        }


    }

}
