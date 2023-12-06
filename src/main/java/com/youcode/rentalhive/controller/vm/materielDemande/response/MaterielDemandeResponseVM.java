package com.youcode.rentalhive.controller.vm.materielDemande.response;

import com.youcode.rentalhive.controller.vm.materiel.response.MaterielResponseVM;
import com.youcode.rentalhive.model.entity.MaterielDemande;

import java.time.LocalDate;

public record MaterielDemandeResponseVM(
        MaterielResponseVM materiel,
        LocalDate debut,
        LocalDate fin
) {
    public static MaterielDemandeResponseVM toVM(MaterielDemande materielDemande){
        return new MaterielDemandeResponseVM(
                MaterielResponseVM.toVM(materielDemande.getMateriel()),
                materielDemande.getDebutLocation(),
                materielDemande.getFinLocation()
        );
    }
}
