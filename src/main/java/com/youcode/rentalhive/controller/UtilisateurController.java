package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.controller.vm.utilisateur.UtilisateurVM;
import com.youcode.rentalhive.model.entity.Utilisateur;
import com.youcode.rentalhive.service.utilisateur.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
    UtilisateurService utilisateurService;
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("{id}")
    public UtilisateurVM getUtilisateurDetail(@PathVariable Long id){
        return utilisateurService.getUtilisateurById(id)
                .map(UtilisateurVM::toVM)
                .orElse(null);
    }

    @GetMapping("/")
    public List<UtilisateurVM> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        return utilisateurs.stream()
                .map(UtilisateurVM::toVM)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<UtilisateurVM> searchUtilisateurs(@RequestParam String searchTerm) {
        List<Utilisateur> utilisateurs = utilisateurService.searchUtilisateurs(searchTerm);
        return utilisateurs.stream()
                .map(UtilisateurVM::toVM)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public UtilisateurVM createUtilisateur(@RequestBody UtilisateurVM utilisateurVM){
        Utilisateur utilisateur = utilisateurVM.toEntite();
        Utilisateur createdUtilisateur = utilisateurService.createUtilisateur(utilisateur);
        return UtilisateurVM.toVM(createdUtilisateur);
    }

    @PutMapping("{id}")
    public UtilisateurVM updateUtilisateur(@PathVariable Long id, @RequestBody UtilisateurVM utilisateurVM) {
        Utilisateur utilisateur = utilisateurVM.toEntite();
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(utilisateur, id);
        return UtilisateurVM.toVM(updatedUtilisateur);
    }

    @DeleteMapping("{id}")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
}
