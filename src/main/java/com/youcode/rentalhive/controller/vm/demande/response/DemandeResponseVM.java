package com.youcode.rentalhive.controller.vm.demande.response;

import com.youcode.rentalhive.controller.vm.materielDemande.response.MaterielDemandeResponseVM;
import com.youcode.rentalhive.model.entity.Demande;

import java.util.List;

public record DemandeResponseVM(
        String reference,
        String statut,
        List<MaterielDemandeResponseVM> materielDemander
) {
    public static DemandeResponseVM toVM(Demande demande){
        return new DemandeResponseVM(
                demande.getReference(),
                demande.getStatutDemande().name(),
                demande.getMaterielDemandeList().stream().map(MaterielDemandeResponseVM::toVM).toList()
        );
    }
}
