package com.youcode.rentalhive.controller.vm.materielDemande.request;

import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Materiel;
import com.youcode.rentalhive.model.entity.MaterielDemande;
import com.youcode.rentalhive.model.entity.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MaterielDemandeRequestVM(
        @NotBlank(message = "Le Type est Obligatoire")
        String type,
        @NotBlank(message = "La Marque est Obligatoire")
        String marque,
        @NotNull(message = "La Date du Debut de Location est obligatoire")
        LocalDate debut,
        @NotNull(message = "La Date de Fin de Location est obligatoire")
        LocalDate fin
) {
    public MaterielDemande toEntite(){
        return  MaterielDemande.builder()
                .materiel(Materiel.builder()
                        .type(Type.builder()
                                .nomType(type)
                                .marque(Marque.builder()
                                        .nomMarque(marque)
                                        .build())
                                .build())
                        .build()
                )
                .debutLocation(debut)
                .finLocation(fin)
                .build();
    }
}
