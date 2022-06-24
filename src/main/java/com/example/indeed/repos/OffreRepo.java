package com.example.indeed.repos;

import com.example.indeed.entities.Offre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffreRepo  extends JpaRepository<Offre,Integer> {
    Page<Offre> findByTitreContains(String search, Pageable pageable);
}
