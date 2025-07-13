package br.edu.ifpr.foz.controle_de_locadora_vhs.conttrollers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    public String add(Model model){

        VHS vhs = new VHS();
        model.addAttribute("vhs", vhs);
        model.addAttribute("statusList", Status.values());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("linkToPost", "/vhs/add");

        return "vhs-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("vhs") VHS vhs, BindingResult fields, Model model, @RequestParam MultipartFile coverImage) throws IOException{
        
        String UPLOAD_DIR = new File("src/main/resources/static/uploads/").getAbsolutePath();

        if(fields.hasErrors()){
            model.addAttribute("vhs", vhs);
            model.addAttribute("statusList", Status.values());

            model.addAttribute("fields", fields);
            model.addAttribute("genres", genreService.findAll());
            return "vhs-form";
        }else{

            if(coverImage != null && !coverImage.isEmpty()){
                String fileName = coverImage.getOriginalFilename();

                Path path = Paths.get(UPLOAD_DIR, fileName);

                Files.createDirectories(path.getParent());
                Files.copy(coverImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                vhs.setImage(fileName);
            }

            vhsService.save(vhs);

            return "redirect:/vhs";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        try {
            vhsService.delete(id);
        } catch (IOException e) {
        }

        return "redirect:/vhs";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){

        Optional<VHS> vhsOptional = vhsService.findById(id);

        if(vhsOptional.isPresent()){
            VHS vhs = vhsOptional.get();

            model.addAttribute("vhs", vhs);
            model.addAttribute("statusList", Status.values());
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("linkToPost", "/vhs/edit");

            return "vhs-form";
        }

        return "redirect:/vhs";
    }

}
