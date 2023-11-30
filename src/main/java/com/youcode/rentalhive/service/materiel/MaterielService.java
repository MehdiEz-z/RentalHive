package com.youcode.rentalhive.service.materiel;

import com.youcode.rentalhive.model.entity.Materiel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterielService {
    Materiel createMateriel(Materiel materiel);
    Materiel getMaterielById(Long id);
    List<Materiel> getAllMateriel();
    Materiel updateMateriel(Materiel materiel, Long id);
    List<Materiel> searchMateriel(String searchTerm);
    void deleteMateriel(Long id);
}
