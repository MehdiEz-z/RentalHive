package com.youcode.rentalhive.service.materiel.implementation;

import com.youcode.rentalhive.handler.exception.OperationException;
import com.youcode.rentalhive.handler.exception.ResourceNotFoundException;
import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Materiel;
import com.youcode.rentalhive.model.entity.Type;
import com.youcode.rentalhive.repository.MaterielRepository;
import com.youcode.rentalhive.service.marque.MarqueService;
import com.youcode.rentalhive.service.materiel.MaterielService;
import com.youcode.rentalhive.service.type.TypeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MaterielServiceImpl implements MaterielService {
    private final MaterielRepository materielRepository;
    private final TypeService typeService;
    private final MarqueService marqueService;

    public MaterielServiceImpl(MaterielRepository materielRepository, TypeService typeService, MarqueService marqueService) {
        this.materielRepository = materielRepository;
        this.typeService = typeService;
        this.marqueService = marqueService;
    }

    @Override
    public Materiel createMateriel(Materiel materiel) {
        if (materielRepository.existsByMatricule(materiel.getMatricule())) {
            throw new OperationException("Le matricule fourni existe déjà");
        }
        Marque existingMarque = marqueService.getMarqueByNom(materiel.getType().getMarque().getNomMarque());
        if (existingMarque == null) {
            throw new ResourceNotFoundException("La marque " + materiel.getType().getMarque().getNomMarque() + " n'existe pas");
        }
        Type existingType = typeService.getTypeByNomAndMarque(materiel.getType().getNomType(), existingMarque);
        materiel.setType(existingType);
        return materielRepository.save(materiel);
    }

    @Override
    public Materiel getMaterielById(Long id) {
        return materielRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le materiel avec l'id : " + id + " n'existe pas"));
    }

    @Override
    public List<Materiel> getAllMateriel() {
        return materielRepository.findAll();
    }

    @Override
    public Materiel updateMateriel(Materiel materiel, Long id) {
        Optional<Materiel> existingMaterielOptional = materielRepository.findById(id);
        if (existingMaterielOptional.isPresent()) {
            Materiel existingMateriel = existingMaterielOptional.get();
            if (!existingMateriel.getMatricule().equals(materiel.getMatricule()) &&
                    materielRepository.existsByMatricule(materiel.getMatricule())) {
                throw new OperationException("Le matricule fourni existe déjà");
            }
            Marque existingMarque = marqueService.getMarqueByNom(materiel.getType().getMarque().getNomMarque());
            if (existingMarque == null) {
                throw new ResourceNotFoundException("La marque " + materiel.getType().getMarque().getNomMarque() + " n'existe pas");
            }
            Type existingType = typeService.getTypeByNomAndMarque(materiel.getType().getNomType(), existingMarque);

            existingMateriel.setMatricule(materiel.getMatricule());
            existingMateriel.setDescription(materiel.getDescription());
            existingMateriel.setPrixLocation(materiel.getPrixLocation());
            existingMateriel.setKilometrage(materiel.getKilometrage());
            existingMateriel.setCapaciteChargeMax(materiel.getCapaciteChargeMax());
            existingMateriel.setTypeCarburant(materiel.getTypeCarburant());
            existingMateriel.setTypePneu(materiel.getTypePneu());
            existingMateriel.setTypeTransmission(materiel.getTypeTransmission());
            existingMateriel.setEtatMateriel(materiel.getEtatMateriel());
            existingMateriel.setType(existingType);
            existingMateriel.getType().setMarque(existingMarque);
            return materielRepository.save(existingMateriel);
        } else {
            throw new ResourceNotFoundException("Le materiel avec l'ID : " + id + " n'existe pas.");
        }
    }

    @Override
    public List<Materiel> searchMateriel(String searchTerm) {
        return materielRepository.findByMatriculeStartingWithIgnoreCaseOrType_Marque_NomMarqueStartingWithIgnoreCaseOrType_NomTypeStartingWithIgnoreCase(
                searchTerm, searchTerm, searchTerm);
    }

    @Override
    public void deleteMateriel(Long id) {
        Optional<Materiel> existingMaterielOptional = materielRepository.findById(id);
        if(existingMaterielOptional.isPresent()){
            materielRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Le Materiel avec l'ID : " + id + " n'existe pas.");
        }
    }
}
