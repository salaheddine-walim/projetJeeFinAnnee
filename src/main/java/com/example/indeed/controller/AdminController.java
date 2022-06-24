package com.example.indeed.controller;

import com.example.indeed.repos.OffreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import com.example.indeed.entities.Offre;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    OffreRepo offreRepo ;

    /**
     *Recuperer la liste des offres dans une dashboard pour faciliter la gestion pour l'admin
     */
    @GetMapping("/admin")
    public String dashboard(Model model,
                            @RequestParam(defaultValue = "0")int page,
                            @RequestParam(defaultValue = "") String search){
            Page<Offre> list_offres= offreRepo.findByTitreContains(search,PageRequest.of(page,6));
            model.addAttribute("list_offres",list_offres);
            model.addAttribute("pages",new int[list_offres.getTotalPages()]);
            model.addAttribute("currentPage",page);
            model.addAttribute("search",search);
        return "admin/dashboard";
    }
    @GetMapping("/admin/delete")
    public String adminDelete(int id){
            offreRepo.deleteById(id);
        return "redirect:/admin";
    }


}
