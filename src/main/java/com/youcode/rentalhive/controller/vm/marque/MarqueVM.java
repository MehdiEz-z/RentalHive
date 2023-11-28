package com.youcode.rentalhive.controller.vm.marque;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.rentalhive.model.entity.Marque;

import java.time.LocalDateTime;

public record MarqueVM(
        String nom,
        String pays,
        @JsonIgnore
        LocalDateTime createdAt
) {
    public static MarqueVM toVM(Marque marque){
        return new MarqueVM(
                marque.getNomMarque(),
                marque.getPaysOrigine(),
                null);
    }

    public Marque toEntite(){
        return Marque.builder()
                .nomMarque(this.nom)
                .paysOrigine(this.pays)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
