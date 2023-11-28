package com.youcode.rentalhive.service.utilisateur;

import com.youcode.rentalhive.model.entity.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UtilisateurService {
    Utilisateur createUtilisateur(Utilisateur utilisateur);
    Optional<Utilisateur> getUtilisateurById(Long id);
    List<Utilisateur> getAllUtilisateurs();
    Utilisateur updateUtilisateur(Utilisateur utilisateur, Long id);
    List<Utilisateur> searchUtilisateurs(String searchTerm);

    void deleteUtilisateur(Long id);
}
