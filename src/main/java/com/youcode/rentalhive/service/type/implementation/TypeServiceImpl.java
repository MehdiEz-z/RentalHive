package com.youcode.rentalhive.service.type.implementation;

import com.youcode.rentalhive.handler.exception.OperationException;
import com.youcode.rentalhive.handler.exception.ResourceNotFoundException;
import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Type;
import com.youcode.rentalhive.repository.TypeRepository;
import com.youcode.rentalhive.service.marque.MarqueService;
import com.youcode.rentalhive.service.type.TypeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final MarqueService marqueService;
    public TypeServiceImpl(TypeRepository typeRepository, MarqueService marqueService) {
        this.typeRepository = typeRepository;
        this.marqueService = marqueService;
    }

    @Override
    public Type createType(Type type) {
        Marque existingMarque = marqueService.getMarqueByNom(type.getMarque().getNomMarque());
        if(existingMarque == null){
            throw new ResourceNotFoundException("La marque "+type.getMarque().getNomMarque()+" n'existe pas");
        }
        List<Type> existingTypes = typeRepository.findByMarqueAndNomTypeIgnoreCase(existingMarque, type.getNomType());
        if (!existingTypes.isEmpty()) {
            throw new OperationException("La marque " + existingMarque.getNomMarque() + " a déjà un type avec le nom " + type.getNomType());
        }
        type.setMarque(existingMarque);
        return typeRepository.save(type);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le Type avec l'id : " + id + " n'existe pas"));
    }

    @Override
    public List<Type> getAllType() {
        return typeRepository.findAll();
    }

    @Override
    public Type updateType(Type type, Long id) {
        Optional<Type> existingTypeOptional = typeRepository.findById(id);
        if (existingTypeOptional.isPresent()) {
            Type existingType = existingTypeOptional.get();
            Marque existingMarque = marqueService.getMarqueByNom(type.getMarque().getNomMarque());
            if (existingMarque == null) {
                throw new ResourceNotFoundException("La marque " + type.getMarque().getNomMarque() + " n'existe pas");
            }
            if (!existingType.getNomType().equals(type.getNomType())) {
                if (typeRepository.findByMarqueAndNomTypeIgnoreCase(existingMarque, type.getNomType()).isEmpty()) {
                    existingType.setNomType(type.getNomType());
                } else {
                    throw new OperationException("Le nom du type fourni existe déjà pour cette marque");
                }
            }
            existingType.setMarque(existingMarque);
            return typeRepository.save(existingType);
        }else {
            throw new ResourceNotFoundException("Le type avec l'ID : " + id + " n'existe pas.");
        }
    }

    @Override
    public List<Type> searchType(String marqueName) {
        return typeRepository.findByMarque_NomMarqueIgnoreCaseStartingWith(marqueName);
    }

    @Override
    public Type getTypeByNom(String nomType) {
        return typeRepository.findByNomType(nomType);
    }

    @Override
    public void deleteType(Long id) {
        Optional<Type> existingTypeOptional = typeRepository.findById(id);
        if(existingTypeOptional.isPresent()){
            typeRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Le Type avec l'ID : " + id + " n'existe pas.");
        }
    }
}
