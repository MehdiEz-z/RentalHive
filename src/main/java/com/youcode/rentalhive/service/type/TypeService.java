package com.youcode.rentalhive.service.type;

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
    void deleteType(Long id);
}
