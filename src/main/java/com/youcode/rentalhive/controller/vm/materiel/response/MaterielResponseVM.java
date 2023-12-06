package com.youcode.rentalhive.controller.vm.materiel.response;

import com.youcode.rentalhive.model.entity.Materiel;

public record MaterielResponseVM(
        String matricul,
        String description,
        Double prix,
        int kilometrage,
        int capacite,
        String carburant,
        String pneu,
        String transmission,
        String marque,
        String type,
        String etat,
        String disponibilite
) {
    public static MaterielResponseVM toVM(Materiel materiel){
        return new MaterielResponseVM(
                materiel.getMatricule(),
                materiel.getDescription(),
                materiel.getPrixLocation(),
                materiel.getKilometrage(),
                materiel.getCapaciteChargeMax(),
                materiel.getTypeCarburant().name(),
                materiel.getTypePneu().name(),
                materiel.getTypeTransmission().name(),
                materiel.getType().getMarque().getNomMarque(),
                materiel.getType().getNomType(),
                materiel.getEtatMateriel().name(),
                materiel.getStatutDisponibilite().name()
        );
    }
}
