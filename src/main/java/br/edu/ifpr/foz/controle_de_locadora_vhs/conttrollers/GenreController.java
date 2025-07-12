package br.edu.ifpr.foz.controle_de_locadora_vhs.conttrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.Genre;
import br.edu.ifpr.foz.controle_de_locadora_vhs.services.GenreService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/genre")
public class GenreController {
    
    @Autowired
    GenreService genreService;

    @GetMapping
    public String genreList(Model model){

        List<Genre> genreList = genreService.findAll();

        model.addAttribute("genreList", genreList);

        return "genre-list";
    }

    @GetMapping("/add")
    public String addForm(Model model){

        Genre genre = new Genre();

        model.addAttribute("genre", genre);

        return "genre-form";
    }


    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("genre") Genre genre, BindingResult fields, Model model){

        if(fields.hasErrors()){

            model.addAttribute("genre", genre);
            model.addAttribute("fields", fields);

            return "genre-form";
        }else{
            genreService.save(genre);
        }

        return "redirect:/genre";
    }


}
