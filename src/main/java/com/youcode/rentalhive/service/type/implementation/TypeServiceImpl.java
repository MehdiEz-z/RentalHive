/* package com.youcode.rentalhive.service.type.implementation;

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
        Optional<Marque> existingMarque = marqueService.getMarqueByNom(type.getMarque().getNomMarque());
        if(existingMarque.isPresent()){
            type.setMarque(existingMarque.get());
            return typeRepository.save(type);
        }
        return null;
    }

    @Override
    public Optional<Type> getTypeById(Long id) {
        return typeRepository.findById(id);
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
            Optional<Marque> existingMarque = marqueService.getMarqueByNom(type.getMarque().getNomMarque());
            if (existingMarque.isPresent()) {
                existingType.setMarque(existingMarque.get());
                existingType.setNomType(type.getNomType());
                return typeRepository.save(existingType);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<Type> searchType(String marqueName) {
        Optional<Marque> existingMarque = marqueService.getMarqueByNom(marqueName);
        return existingMarque.map(typeRepository::findByMarque).orElse(null);
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}*/
