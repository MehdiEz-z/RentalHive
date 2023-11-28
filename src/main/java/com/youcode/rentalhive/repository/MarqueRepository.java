package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarqueRepository extends JpaRepository<Marque, Long> {
    List<Marque> findByNomMarqueStartingWithIgnoreCaseOrPaysOrigineStartingWithIgnoreCase(String nom, String pays);
    Marque findByNomMarqueIgnoreCase(String nom);

}
