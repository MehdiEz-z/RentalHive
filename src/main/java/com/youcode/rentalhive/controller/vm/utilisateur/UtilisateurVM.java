package com.youcode.rentalhive.controller.vm.utilisateur;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.rentalhive.model.entity.Utilisateur;

import java.time.LocalDateTime;

public record UtilisateurVM(
        String nom,
        String email,
        @JsonIgnore
        LocalDateTime createdAt
) {
    public static UtilisateurVM toVM(Utilisateur utilisateur){
        return new UtilisateurVM(
                utilisateur.getNomUtilisateur(),
                utilisateur.getEmail(),
                null);
    }

    public Utilisateur toEntite(){
        return Utilisateur.builder()
                .nomUtilisateur(this.nom)
                .email(this.email)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
