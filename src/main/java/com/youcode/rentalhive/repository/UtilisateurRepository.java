package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.model.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    List<Utilisateur> findByNomUtilisateurStartingWithIgnoreCaseOrEmailStartingWithIgnoreCase(String nom, String email);

}
