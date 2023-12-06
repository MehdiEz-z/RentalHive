package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.model.entity.Materiel;
import com.youcode.rentalhive.model.enums.materiel.StatutDisponibilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface MaterielRepository extends JpaRepository<Materiel, Long> {
    boolean existsByMatricule(String matricule);
    Materiel findByMatricule(String matricule);
    List<Materiel> findByMatriculeStartingWithIgnoreCaseOrType_Marque_NomMarqueStartingWithIgnoreCaseOrType_NomTypeStartingWithIgnoreCase(
            String matricule, String marque, String type);
    List<Materiel> findByType_NomTypeIgnoreCaseAndType_Marque_NomMarqueIgnoreCase(String nomType, String nomMarque);

    @Modifying
    @Transactional
    @Query("update Materiel m set m.statutDisponibilite = :status where m.idMateriel = :id")
    void updateByStatutDisponibilite(@Param("status") StatutDisponibilite status, @Param("id") Long id);

}
