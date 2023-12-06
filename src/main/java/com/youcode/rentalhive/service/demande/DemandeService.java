package com.youcode.rentalhive.service.demande;

import com.youcode.rentalhive.model.entity.Demande;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DemandeService {
    Demande createDemande(Demande demande);
    Demande getDemandeById(Long id);
    String getReferenceDemande();
    List<Demande> getAllDemande();
}
