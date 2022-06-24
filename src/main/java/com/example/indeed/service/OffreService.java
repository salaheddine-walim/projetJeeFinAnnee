package com.example.indeed.service;

import com.example.indeed.entities.Offre;
import com.example.indeed.repos.OffreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OffreService {

    @Autowired
    private OffreRepo offreRepo;

    public List<Offre> getAllOffres(){
        return offreRepo.findAll();
    }
}
