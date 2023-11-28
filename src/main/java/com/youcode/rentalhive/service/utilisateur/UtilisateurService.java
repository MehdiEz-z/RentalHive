package com.youcode.rentalhive.service.utilisateur;

import com.youcode.rentalhive.model.entity.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UtilisateurService {
    Utilisateur createUtilisateur(Utilisateur utilisateur);
    Utilisateur getUtilisateurById(Long id);
    List<Utilisateur> getAllUtilisateurs();
    Utilisateur updateUtilisateur(Utilisateur utilisateur, Long id);
    void deleteUtilisateur(Long id);
}
