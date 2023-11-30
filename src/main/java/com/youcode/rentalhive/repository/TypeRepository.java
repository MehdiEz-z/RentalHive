package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByNomType(String nomType);
    List<Type> findByNomTypeStartingWithIgnoreCaseOrMarque_NomMarqueStartingWithIgnoreCase(String nomType, String marqueName);
    List<Type> findByMarqueAndNomTypeIgnoreCase(Marque marque, String nomType);
}
