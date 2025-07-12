package br.edu.ifpr.foz.controle_de_locadora_vhs.conttrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpr.foz.controle_de_locadora_vhs.dto.GenreWithVHSCount;
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

        List<GenreWithVHSCount> genreList = genreService.findAllWithVhsCount();

        model.addAttribute("genreList", genreList);

        return "genre-list";
    }

    @GetMapping("/add")
    public String add(Model model){

        Genre genre = new Genre();

        model.addAttribute("genre", genre);

        return "genre-form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){

        Optional<Genre> genre = genreService.findById(id);

        if(genre.isPresent()){
            model.addAttribute("genre", genre.get());

            return "genre-form";
        }

        return "redirect:/genre";


    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model){

        genreService.delete(id);

        return "redirect:/genre";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("genre") Genre genre, BindingResult fields, Model model){

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
