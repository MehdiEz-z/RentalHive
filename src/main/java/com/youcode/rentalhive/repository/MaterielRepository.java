package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.model.entity.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MaterielRepository extends JpaRepository<Materiel, Long> {
    boolean existsByMatricule(String matricule);
    List<Materiel> findByMatriculeStartingWithIgnoreCaseOrType_Marque_NomMarqueStartingWithIgnoreCaseOrType_NomTypeStartingWithIgnoreCase(
            String matricule, String marque, String type);
}
