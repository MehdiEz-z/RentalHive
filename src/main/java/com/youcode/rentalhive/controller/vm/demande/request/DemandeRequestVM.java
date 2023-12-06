package com.youcode.rentalhive.controller.vm.demande.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.rentalhive.controller.vm.materielDemande.request.MaterielDemandeRequestVM;
import com.youcode.rentalhive.model.entity.Demande;
import com.youcode.rentalhive.model.entity.MaterielDemande;
import com.youcode.rentalhive.model.enums.demande.StatutDemande;
import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record DemandeRequestVM(
        @JsonIgnore
        String reference,
        @JsonIgnore
        String statut,
        @Valid
        List<MaterielDemandeRequestVM> materielDemander
) {
    public Demande toEntite(){
        List<MaterielDemande> materielDemandes = Optional.ofNullable(materielDemander)
                .orElse(Collections.emptyList())
                .stream()
                .map(MaterielDemandeRequestVM::toEntite)
                .collect(Collectors.toList());

        return Demande.builder()
                .statutDemande(StatutDemande.EN_ATTENTE)
                .materielDemandeList(materielDemandes)
                .build();
    }
}
