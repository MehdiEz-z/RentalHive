package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.controller.vm.utilisateur.UtilisateurVM;
import com.youcode.rentalhive.handler.response.ResponseMessage;
import com.youcode.rentalhive.model.entity.Utilisateur;
import com.youcode.rentalhive.service.utilisateur.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUtilisateurDetail(@PathVariable Long id) {
        UtilisateurVM utilisateurVM = UtilisateurVM.toVM(utilisateurService.getUtilisateurById(id));
        return ResponseMessage.ok(utilisateurVM, "Utilisateur Récuperé avec Succé");
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        if(utilisateurs.isEmpty()){
            return ResponseMessage.notFound("Aucun utilisateur trouvé");
        }else{
            return ResponseMessage.ok(utilisateurs.stream()
                    .map(UtilisateurVM::toVM)
                    .collect(Collectors.toList()),"Utilisateurs Récuperés avec Succé");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUtilisateurs(@RequestParam String searchTerm) {
        if (searchTerm == null || searchTerm.isBlank()) {
            return ResponseMessage.badRequest("Le terme de recherche ne peut pas être vide.");
        }
        List<Utilisateur> utilisateurs = utilisateurService.searchUtilisateurs(searchTerm);
        if(utilisateurs.isEmpty()){
            return ResponseMessage.notFound("Aucun utilisateur trouvé pour le terme de recherche : " + searchTerm);
        }else{
            return ResponseMessage.ok(utilisateurs.stream()
                    .map(UtilisateurVM::toVM)
                    .collect(Collectors.toList()),"Utilisateurs Récuperer avec Succée");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createUtilisateur(@Valid @RequestBody UtilisateurVM utilisateurVM){
        Utilisateur utilisateur = utilisateurVM.toEntite();
        Utilisateur createdUtilisateur = utilisateurService.createUtilisateur(utilisateur);
        return ResponseMessage.created(
                UtilisateurVM.toVM(createdUtilisateur),
                "Utilisateur Crée Avec Succée");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUtilisateur(@PathVariable Long id, @Valid @RequestBody UtilisateurVM utilisateurVM) {
        Utilisateur utilisateur = utilisateurVM.toEntite();
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(utilisateur, id);
        return ResponseMessage.ok(
                UtilisateurVM.toVM(updatedUtilisateur),
                "Utilisateur Modifé Avec Succée");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseMessage.ok(null,"Utilisateur Supprimé avec Succé");
    }
}
