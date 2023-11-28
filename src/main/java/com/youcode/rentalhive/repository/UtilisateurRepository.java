package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.model.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
