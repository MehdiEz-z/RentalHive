package com.youcode.rentalhive.controller.vm.materiel.request;

import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Materiel;
import com.youcode.rentalhive.model.entity.Type;
import com.youcode.rentalhive.model.enums.materiel.*;
import jakarta.validation.constraints.*;

public record MaterielRequestVM(
        @NotBlank(message = "Le Matricule est Obligatoire")
        String matricul,
        @NotBlank(message = "La Description est Obligatoire")
        @Pattern(regexp = "^[.,\\p{L}0-9\\s]+$", message = "La Description ne doit pas contenir des caractére spéciaux")
        @Size(max = 200, message = "La Description ne doit pas dépassé 200 caractère")
        String description,
        @NotNull(message = "Le prix journalier de location est Obligatoire")
        @Digits(integer = 10, fraction = 2, message = "Le prix doit être une valeur numérique avec jusqu'à 2 décimales")
        @Positive(message = "Le prix doit être supérieur a 0")
        Double prix,
        @Min(value =1, message = "Le kilometrage du materiel est Obligatoire")
        @Positive(message = "Le kilometrage du materiel doit être supérieur a 0")
        @Digits(integer = 10, fraction = 0, message = "Le kilometrage du materiel doit être un nombre entier positif")
        int kilometrage,
        @Min(value =1, message = "La capacité maximale de charge est Obligatoire")
        @Positive(message = "La capacité maximale de charge doit être supérieur a 0")
        @Digits(integer = 10, fraction = 0, message = "La capacité maximale de charge doit être un nombre entier positif")
        int capacite,
        @NotBlank(message = "Le type du carburant est Obligatoire")
        @Pattern(regexp = "^(ESSENCE|DIESEL|ELECTRIQUE)$", message = "Le type du carburant doit être soit ESSENCE, DIESEL ou ELECTRIQUE")
        String carburant,
        @NotBlank(message = "Le type des pneus est Obligatoire")
        @Pattern(regexp = "^(ROUTE|TOUT_TERRAIN)$", message = "Le type du pneu doit être soit ROUTE ou TOUT_TERRAIN")
        String pneu,
        @NotBlank(message = "La Transmission est Obligatoire")
        @Pattern(regexp = "^(MANUELLE|AUTOMATIQUE)$", message = "Le type du carburant doit être soit MANUELLE ou AUTOMATIQUE")
        String transmission,
        @NotBlank(message = "La Marque est Obligatoire")
        String marque,
        @NotBlank(message = "Le Type est Obligatoire")
        String type,
        @Pattern(regexp = "^(EN_SERVICE|EN_MAINTENANCE|HORS_SERVICE)$", message = "L'etat du materiel doit être soit EN_SERVICE, EN_MAINTENANCE ou HORS_SERVICE")
        String etat
) {
        public Materiel toEntite(){
                return Materiel.builder()
                        .matricule(matricul)
                        .description(description)
                        .prixLocation(prix)
                        .kilometrage(kilometrage)
                        .capaciteChargeMax(capacite)
                        .typeCarburant(TypeCarburant.valueOf(carburant))
                        .typePneu(TypePneu.valueOf(pneu))
                        .typeTransmission(TypeTransmission.valueOf(transmission))
                        .etatMateriel(EtatMateriel.EN_SERVICE)
                        .statutDisponibilite(StatutDisponibilite.DISPONIBLE)
                        .type(Type.builder()
                                .nomType(type)
                                .marque(Marque.builder()
                                        .nomMarque(marque)
                                        .build())
                                .build()
                        )
                        .build();
        }

        public Materiel toUpdateEntite(){
                return Materiel.builder()
                        .matricule(matricul)
                        .description(description)
                        .prixLocation(prix)
                        .kilometrage(kilometrage)
                        .capaciteChargeMax(capacite)
                        .typeCarburant(TypeCarburant.valueOf(carburant))
                        .typePneu(TypePneu.valueOf(pneu))
                        .typeTransmission(TypeTransmission.valueOf(transmission))
                        .etatMateriel(EtatMateriel.valueOf(etat))
                        .type(Type.builder()
                                .nomType(type)
                                .marque(Marque.builder()
                                        .nomMarque(marque)
                                        .build())
                                .build()
                        )
                        .build();
        }
}
