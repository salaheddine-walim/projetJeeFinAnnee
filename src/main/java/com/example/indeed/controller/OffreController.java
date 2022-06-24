package com.example.indeed.controller;

import com.example.indeed.entities.Offre;
import com.example.indeed.repos.OffreRepo;
import com.example.indeed.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class OffreController {

    @Autowired
    private OffreRepo offreRepo;

    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN")));
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return "redirect:/admin";
        }
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String home(Model model) {
        List<Offre> listOffres=offreRepo.findAll();
        model.addAttribute("offres",listOffres);
        return "list";
    }

    @Autowired
    private UserRepo userRepo;
    @GetMapping("/add_offre")
    public String addOffre(){


        return "ajouterOffre";
    }

    @PostMapping("/add_offre")
    public String saveOffre( Offre o){
        o.setPoster(userRepo.findById(1).get());
        offreRepo.save(o);
        return "redirect:/index";
    }

    @GetMapping("/offre/modify")
    public String modOffre(Model model,Integer id){
        Offre offre = offreRepo.findById(id).get();
        if(offre==null)
            return "redirect:/index";
        else
            model.addAttribute("offre",offre);
        return "modifierOffre";
    }

    @GetMapping("/offre/show")
    public String showOffre(Model model,Integer id){
        Offre offre = offreRepo.findById(id).get();
               // isPresent() ? offreRepo.findById(1).get(): null;
        /*if(offre==null)
            return "redirect:/index";
        else*/
            model.addAttribute("offre",offre);
            return "detailOffre";
    }

    @PostMapping("/offre/modify")
    public String modifyOffre(Offre o,Integer id){
        Offre offre =offreRepo.findById(id).get();
        offre.setTitre(o.getTitre());
        offre.setDescription(o.getDescription());
        offre.setDomaine(o.getDomaine());
        offreRepo.save(offre);

        return "redirect:/index";
    }



    


}
