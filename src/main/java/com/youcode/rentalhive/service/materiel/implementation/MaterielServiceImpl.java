package com.youcode.rentalhive.service.materiel.implementation;

import com.youcode.rentalhive.handler.exception.OperationException;
import com.youcode.rentalhive.handler.exception.ResourceNotFoundException;
import com.youcode.rentalhive.model.entity.Materiel;
import com.youcode.rentalhive.model.entity.Type;
import com.youcode.rentalhive.repository.MaterielRepository;
import com.youcode.rentalhive.service.materiel.MaterielService;
import com.youcode.rentalhive.service.type.TypeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MaterielServiceImpl implements MaterielService {
    private final MaterielRepository materielRepository;
    private final TypeService typeService;

    public MaterielServiceImpl(MaterielRepository materielRepository, TypeService typeService) {
        this.materielRepository = materielRepository;
        this.typeService = typeService;
    }

    @Override
    public Materiel createMateriel(Materiel materiel) {
        if (materielRepository.existsByMatricule(materiel.getMatricule())) {
            throw new OperationException("Le matricule fourni existe déjà");
        }
        Type existingType = typeService.getTypeByNom(materiel.getType().getNomType());
        if (existingType == null) {
            throw new ResourceNotFoundException("Le type " + materiel.getType().getNomType() + " n'existe pas");
        }
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
            Type existingType = typeService.getTypeByNom(materiel.getType().getNomType());
            if (existingType == null) {
                throw new ResourceNotFoundException("Le type " + materiel.getType().getNomType() + " n'existe pas");
            }
            materiel.setType(existingType);
            return materielRepository.save(existingMateriel);
        }else {
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
