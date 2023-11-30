package com.youcode.rentalhive.service.type;

import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TypeService {
    Type createType(Type type);
    Type getTypeById(Long id);
    List<Type> getAllType();
    Type updateType(Type type, Long id);
    List<Type> searchType(String searchTerm);
    Type getTypeByNom(String nomType);
    Type getTypeByNomAndMarque(String nomType, Marque marque);
    void deleteType(Long id);
}
