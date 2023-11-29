package com.youcode.rentalhive.controller.vm.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.rentalhive.controller.vm.marque.MarqueVM;
import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Type;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TypeVM(
    @NotBlank(message = "Le Nom est Obligatoire")
    String nom,
    @NotBlank(message = "La Marque est Obligatoire")
    String marqueNom,
    @JsonIgnore
    LocalDateTime createdAt
) {
    public static TypeVM toVM(Type type ){
        return new TypeVM(
                type.getNomType(),
                type.getMarque().getNomMarque(),
                null);
    }

    public Type toEntite(){
        return Type.builder()
                .nomType(this.nom)
                .marque(Marque.builder()
                        .nomMarque(marqueNom)
                        .build()
                )
                .createdAt(LocalDateTime.now())
                .build();
    }
}
