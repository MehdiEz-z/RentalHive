package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.controller.vm.utilisateur.UtilisateurVM;
import com.youcode.rentalhive.model.entity.Utilisateur;
import com.youcode.rentalhive.service.utilisateur.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UtilisateurController {
    UtilisateurService utilisateurService;

    @GetMapping("{id}")
    public UtilisateurVM getUtilisateurDetail(@PathVariable Long id){
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        return UtilisateurVM.toVM(utilisateur);
    }
}
