package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.model.entity.MaterielDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterielDemandeRepository extends JpaRepository<MaterielDemande, Long> {
}
