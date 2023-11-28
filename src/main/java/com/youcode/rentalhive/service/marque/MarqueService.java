package com.youcode.rentalhive.service.marque;

import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MarqueService {
    Marque createMarque(Marque marque);
    Optional<Marque> getMarqueById(Long id);
    Optional<Marque> getMarqueByNom(String nom);
    List<Marque> getAllMarque();
    Marque updateMarque(Marque marque, Long id);
    List<Marque> searchMarque(String searchTerm);
    void deleteMarque(Long id);
}
