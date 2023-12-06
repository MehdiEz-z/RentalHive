package com.youcode.rentalhive.service.demande.implementation;

import com.youcode.rentalhive.model.entity.Demande;
import com.youcode.rentalhive.model.entity.Materiel;
import com.youcode.rentalhive.model.entity.MaterielDemande;
import com.youcode.rentalhive.model.enums.materiel.StatutDisponibilite;
import com.youcode.rentalhive.repository.DemandeRepository;
import com.youcode.rentalhive.repository.MaterielDemandeRepository;
import com.youcode.rentalhive.service.demande.DemandeService;
import com.youcode.rentalhive.service.materiel.MaterielService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
public class DemandeServiceImpl implements DemandeService {

    private final DemandeRepository demandeRepository;
    private final MaterielService materielService;
    private final MaterielDemandeRepository materielDemandeRepository;

    public DemandeServiceImpl(DemandeRepository demandeRepository, MaterielService materielService, MaterielDemandeRepository materielDemandeRepository) {
        this.demandeRepository = demandeRepository;
        this.materielService = materielService;
        this.materielDemandeRepository = materielDemandeRepository;
    }

    @Override
    @Transactional
    public Demande createDemande(Demande demande) {
        List<MaterielDemande> materielDemandes = demande.getMaterielDemandeList();
        for (MaterielDemande materielDemande : materielDemandes) {
            LocalDate currentDate = LocalDate.now();
            LocalDate debutLocation = materielDemande.getDebutLocation();
            LocalDate finLocation = materielDemande.getFinLocation();
            if (debutLocation.isBefore(currentDate)) {
                throw new IllegalArgumentException("La date de début doit être égale ou postérieure à la date actuelle.");
            }
            if (finLocation.isBefore(debutLocation)) {
                throw new IllegalArgumentException("La date de fin doit être égale ou postérieure à la date de début.");
            }
        materielDemandes.forEach(materielDemande2 -> {
            String nomMarque = materielDemande2.getMateriel().getType().getMarque().getNomMarque();
            String nomType = materielDemande2.getMateriel().getType().getNomType();
            Materiel materiel = materielService.getMaterielByDisponibilite(nomType, nomMarque);
            if (materiel.getStatutDisponibilite() != StatutDisponibilite.DISPONIBLE) {
                throw new IllegalStateException("Le matériel n'est pas disponible pour la période demandée.");
            }
            materiel.setStatutDisponibilite(StatutDisponibilite.EN_COURS_DE_RESERVATION);
            materielService.updateByStatutDisponibilite(materiel.getStatutDisponibilite(), materiel.getIdMateriel());
            materielDemande2.setMateriel(materiel);
            materielDemande2.setDemande(demande);
        });
        }
        Demande demandeSaved = demandeRepository.save(demande);
        materielDemandeRepository.saveAll(materielDemandes);
        demandeSaved.setMaterielDemandeList(materielDemandes);
        return demandeSaved;
    }

    @Override
    public Demande getDemandeById(Long id) {
        return null;
    }

    @Override
    public String getReferenceDemande() {
        return null;
    }

    @Override
    public List<Demande> getAllDemande() {
        return null;
    }
}
