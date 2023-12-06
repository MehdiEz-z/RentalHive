package com.youcode.rentalhive.controller.vm.utilisateur;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.rentalhive.model.entity.Utilisateur;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record UtilisateurVM(
        @NotBlank(message = "Le Nom est Obligatoire")
        String nom,
        @NotBlank(message = "L'email est Obligatoire")
        @Pattern(regexp = "^[A-Za-z0-9._-]+@(gmail|outlook|hotmail)\\.(com|net|fr)$", message = "Email Invalid")
        String email
) {
    public static UtilisateurVM toVM(Utilisateur utilisateur){
        return new UtilisateurVM(
                utilisateur.getNomUtilisateur(),
                utilisateur.getEmail());
    }

    public Utilisateur toEntite(){
        return Utilisateur.builder()
                .nomUtilisateur(this.nom)
                .email(this.email)
                .build();
    }
}
