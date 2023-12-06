package com.youcode.rentalhive.service.materiel;

import com.youcode.rentalhive.model.entity.Materiel;
import com.youcode.rentalhive.model.enums.materiel.StatutDisponibilite;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterielService {
    Materiel createMateriel(Materiel materiel);
    Materiel getMaterielById(Long id);
    Materiel getMaterielByMatricule(String matricule);
    List<Materiel> getAllMateriel();
    Materiel updateMateriel(Materiel materiel, Long id);
    List<Materiel> searchMateriel(String searchTerm);
    Materiel getMaterielByDisponibilite(String type, String mark);
    void deleteMateriel(Long id);

    void updateByStatutDisponibilite(StatutDisponibilite statutDisponibilite, Long id);
}
