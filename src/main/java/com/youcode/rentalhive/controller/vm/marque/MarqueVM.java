package com.youcode.rentalhive.controller.vm.marque;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.rentalhive.model.entity.Marque;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record MarqueVM(
        @NotBlank(message = "Le Nom est Obligatoire")
        String marque,
        @NotBlank(message = "Le Pays est Obligatoire")
        String pays
) {
    public static MarqueVM toVM(Marque marque){
        return new MarqueVM(
                marque.getNomMarque(),
                marque.getPaysOrigine());
    }

    public Marque toEntite(){
        return Marque.builder()
                .nomMarque(this.marque)
                .paysOrigine(this.pays)
                .build();
    }
}
