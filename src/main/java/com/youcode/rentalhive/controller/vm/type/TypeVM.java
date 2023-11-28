package com.youcode.rentalhive.controller.vm.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.rentalhive.controller.vm.marque.MarqueVM;
import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Type;

import java.time.LocalDateTime;

public record TypeVM(
    String nom,
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
